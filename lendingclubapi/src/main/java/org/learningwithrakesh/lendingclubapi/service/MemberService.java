package org.learningwithrakesh.lendingclubapi.service;

import java.util.List;

import org.learningwithrakesh.lendingclubapi.dto.FilterColumn;
import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;

public interface MemberService {
	
	public Member getById(long id);
	
	public MemberPaginator getPaginatedMembers(String query, int currentPage, int recordSize, FilterColumn []filterCols);

}
