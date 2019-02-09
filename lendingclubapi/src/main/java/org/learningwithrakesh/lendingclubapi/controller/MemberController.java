package org.learningwithrakesh.lendingclubapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.learningwithrakesh.lendingclubapi.dto.FilterColumn;
import org.learningwithrakesh.lendingclubapi.dto.MemberPaginator;
import org.learningwithrakesh.lendingclubapi.entity.Member;
import org.learningwithrakesh.lendingclubapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RestController()
@CrossOrigin()
public class MemberController {
	@Autowired
	MemberService memberService;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Member getMember(@PathVariable("id") long memberId) {
		return this.memberService.getById(memberId);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public MemberPaginator gePaginatedMember(@RequestParam(value = "q", required = false) String query,
			@RequestParam("currentPage") int currentPage, @RequestParam("recordSize") int recordSize,
			@RequestParam(value = "home_ownership", required = false) String homeOwnership,
			@RequestParam(value = "verification_status", required = false) String verificationStatus,
			@RequestParam(value = "loan_status", required = false) String loanStatus,
			@RequestParam(value = "grade", required = false) String grade) throws Exception {
		if (currentPage <= 0 || currentPage <= 0)
			throw new Exception("PageSize or recordSize must be greater than zero");
		List<FilterColumn> filterColumns = new ArrayList<>();
		if (homeOwnership != null) {
			filterColumns.add(new FilterColumn("homeOwnership", homeOwnership));
		}
		if (verificationStatus != null) {
			filterColumns.add(new FilterColumn("verificationStatus", verificationStatus));
		}
		if (loanStatus != null) {
			filterColumns.add(new FilterColumn("loadStatus", loanStatus));
		}
		if (grade != null) {
			filterColumns.add(new FilterColumn("grade", grade));
		}
		return this.memberService.getPaginatedMembers(query,--currentPage, recordSize,
				filterColumns.toArray(new FilterColumn[filterColumns.size()]));
	}
}
