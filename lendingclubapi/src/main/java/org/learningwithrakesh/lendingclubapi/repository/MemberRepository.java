package org.learningwithrakesh.lendingclubapi.repository;

import java.util.List;

import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;

public interface MemberRepository extends CurdRepository<Member>  {
	public MemberPaginator getAllPaginator(int currentPage, int recordSize);
}
