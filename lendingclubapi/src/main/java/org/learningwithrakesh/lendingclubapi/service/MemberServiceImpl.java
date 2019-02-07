package org.learningwithrakesh.lendingclubapi.service;

import java.util.List;

import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;
import org.learningwithrakesh.lendingclubapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository repo;

	public List<Member> getAll() {
		return repo.getAll();
	}

	public Member save(Member member) {
		try {
			return repo.save(member);
		} catch (Exception ex) {
			// just ignore wrong format data;
			return null;
		}
	}

	public Member getById(long id) {
		return repo.getById(id);
	}

	public MemberPaginator getAllPaginator(int currentPage, int recordSize) {
		return this.repo.getAllPaginator(currentPage, recordSize);
	}

}
