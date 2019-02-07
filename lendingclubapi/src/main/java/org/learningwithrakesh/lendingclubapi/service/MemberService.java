package org.learningwithrakesh.lendingclubapi.service;

import java.util.List;

import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;

public interface MemberService {
	public List<Member> getAll();

	public MemberPaginator getAllPaginator(int currentPage, int recordSize);

	public Member save(Member member);

	public Member getById(long id);

}
