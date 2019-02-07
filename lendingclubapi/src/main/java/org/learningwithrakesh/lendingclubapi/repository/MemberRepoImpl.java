package org.learningwithrakesh.lendingclubapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberRepoImpl implements MemberRepository{
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Member> getAll() {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		List<Member> members = openSession.createQuery("from Member").list();
		openSession.getTransaction().commit();
		openSession.close();
		return members;
	}

	public  Member save(Member data) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Long id  = (Long)openSession.save(data);
		openSession.getTransaction().commit();
		Member saveMember = (Member) openSession.load(Member.class, id);
		openSession.close();
		return saveMember;
	}

	public Member getById(Long id) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Member member = (Member) openSession.byId(Member.class).load(id);
		openSession.getTransaction().commit();
		openSession.close();
		return member;
	}

	public MemberPaginator getAllPaginator(int currentPage, int recordSize) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		long totalRecords = (Long)openSession.createQuery("select count(*) from Member").uniqueResult();
		Query query = openSession.createQuery("from Member");
		query.setFirstResult(currentPage*recordSize + 1);
		query.setMaxResults(recordSize);
		List<Member> members = query.list();
		MemberPaginator mp = new MemberPaginator();
		mp.setCurrentPage(currentPage+1);
		mp.setRecordSize((long)members.size());;
		mp.setTotalRecords(totalRecords);
		mp.setContents(members);
		openSession.close();
		return mp;
	}

}
