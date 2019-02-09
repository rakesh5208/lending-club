package org.learningwithrakesh.lendingclubapi.repository;

import java.util.List;

import org.learningwithrakesh.lendingclubapi.dto.FilterColumn;
import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;

public interface MemberRepository extends CurdRepository<Member> {
	public MemberPaginator getPaginatedMembers(String query, int currentPage, int recordSize, FilterColumn []filterCols);
}
