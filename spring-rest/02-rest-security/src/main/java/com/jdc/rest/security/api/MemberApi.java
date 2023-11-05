package com.jdc.rest.security.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.rest.security.model.dto.AccessHistoryDto;
import com.jdc.rest.security.model.dto.ApiResponse;
import com.jdc.rest.security.model.dto.ProfileDto;
import com.jdc.rest.security.service.AccessHistoryService;
import com.jdc.rest.security.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberApi {
	
	@Autowired
	private MemberService service;
	@Autowired
	private AccessHistoryService accessHistoryService;

	@GetMapping("profile/{username}")
	public ApiResponse<ProfileDto> getProfile(@PathVariable String username) {
		return ApiResponse.success(service.getProfile(username));
	}
	
	@GetMapping("access")
	public ApiResponse<List<AccessHistoryDto>> getAccessHistory(
			@RequestParam Optional<String> username,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to
			) {
		return ApiResponse.success(accessHistoryService.getAccessHistory(username, from, to));
	}	
}
