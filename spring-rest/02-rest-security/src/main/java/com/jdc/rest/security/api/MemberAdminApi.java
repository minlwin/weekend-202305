package com.jdc.rest.security.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.rest.security.model.dto.ApiResponse;
import com.jdc.rest.security.model.dto.MemberDto;
import com.jdc.rest.security.service.MemberService;

@RestController
@RequestMapping("admin/members")
public class MemberAdminApi {
	
	@Autowired
	private MemberService memberService;

	@GetMapping
	public ApiResponse<List<MemberDto>> search(
			@RequestParam Optional<String> name,
			@RequestParam Optional<LocalDate> from,
			@RequestParam Optional<LocalDate> to) {
		return ApiResponse.success(memberService.search(name, from, to));
	}
}
