package org.learningwithrakesh.lendingclubapi.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.learningwithrakesh.lendingclubapi.dto.FilterColumn;
import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
@Transactional
public class MemberRepoImpl implements MemberRepository {
	@Autowired
	SessionFactory sessionFactory;

	public List<Member> getAll() {
		return null;
	}

	public Member save(Member data) {
		return null;
	}

	public Member getById(Long id) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Member member = (Member) openSession.byId(Member.class).load(id);
		openSession.getTransaction().commit();
		openSession.close();
		return member;
	}

	
	public MemberPaginator getPaginatedMembers(String query, int currentPage, int recordSize,
			FilterColumn[] filterColums) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Criteria createdCriteriaRowCount = createCriteria(openSession, filterColums);
		Criteria createdCriteriaList = createCriteria(openSession, filterColums);
		long totalRecords = 0;
		if (query != null && !query.isEmpty()) {	
			// total count
			totalRecords = (Long) createdCriteriaRowCount.add(Restrictions.and(likeQueryRestristriction(query)))
					.setProjection(Projections.rowCount()).uniqueResult();
			// list values
			createdCriteriaList.add(Restrictions.and(likeQueryRestristriction(query)));
		} else {
			totalRecords = (Long) createdCriteriaRowCount.setProjection(Projections.rowCount())
					.uniqueResult();
		}

		createdCriteriaList.setFirstResult(currentPage * recordSize + 1);
		createdCriteriaList.setMaxResults(recordSize);

		List<Member> members = createdCriteriaList.list();
		// createCriteria.setProjection(null);

		MemberPaginator mp = new MemberPaginator();
		mp.setCurrentPage(currentPage + 1);
		mp.setRecordSize((long) members.size());
		mp.setTotalRecords(totalRecords);
		mp.setContents(members);
		openSession.close();
		return mp;
	}

	private Criteria createCriteria(Session openSession, FilterColumn[] filterColums) {
		Criteria createdCriteria = openSession.createCriteria(Member.class);
		createdCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Disjunction> andExp = new ArrayList<>();
		for (FilterColumn fc : filterColums) {
			String[] colValues = fc.getValue().split("\\|");
			List<SimpleExpression> orExp = new ArrayList<>();
			for (String colValue : colValues) {
				orExp.add(Restrictions.eq(fc.getCol(), colValue));
			}
			andExp.add(Restrictions.or(orExp.toArray(new SimpleExpression[orExp.size()])));
		}

		createdCriteria.add(Restrictions.and(andExp.toArray(new Disjunction[andExp.size()])));
		return createdCriteria;
	}

	public Disjunction likeQueryRestristriction(String query) {
		String[] colTypeString = { "term", "grade", "empTitle", "empLength", "homeOwnership", "verificationStatus",
				"issueDate", "loadStatus", "description", "purpose", "title", "addrState", "lastPymntDate" };
		String[] colTypeLong = { "id" };
		List<SimpleExpression> orExp = new ArrayList<>();
		for (String col : colTypeString) {
			orExp.add(Restrictions.like(col, query, MatchMode.ANYWHERE));
		}
		return Restrictions.or(orExp.toArray(new SimpleExpression[orExp.size()]));
	}
}
