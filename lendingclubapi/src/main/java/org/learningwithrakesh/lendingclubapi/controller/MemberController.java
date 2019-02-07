package org.learningwithrakesh.lendingclubapi.controller;

import java.util.ArrayList;
import java.util.List;

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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public MemberPaginator getAll(@RequestParam("currentPage") int currentPage, @RequestParam("recordSize") int recordSize)
			throws Exception {
		if (currentPage <= 0 || currentPage <= 0)
			throw new Exception("PageSize or recordSize must be greater than zero");
		return this.memberService.getAllPaginator(--currentPage, recordSize);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Member getMember(@PathVariable("id") long memberId) {
		return this.memberService.getById(memberId);
	}

}
