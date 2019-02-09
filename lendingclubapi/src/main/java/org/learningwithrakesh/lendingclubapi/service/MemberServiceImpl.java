package org.learningwithrakesh.lendingclubapi.service;

import java.util.List;

import org.learningwithrakesh.lendingclubapi.dto.FilterColumn;
import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;
import org.learningwithrakesh.lendingclubapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository repo;
	
	public Member getById(long id) {
		return repo.getById(id);
	}

	@Override
	public MemberPaginator getPaginatedMembers(String query, int currentPage, int recordSize,
			FilterColumn[] filterCols) {
		return repo.getPaginatedMembers(query, currentPage, recordSize, filterCols);
	}

}
