package com.jdc.rest.security.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.rest.security.model.dto.AccessHistoryDto;
import com.jdc.rest.security.model.dto.ApiResponse;
import com.jdc.rest.security.model.dto.ProfileDto;

@RestController
@RequestMapping("member")
public class MemberApi {

	@GetMapping("profile")
	public ApiResponse<ProfileDto> getProfile() {
		return ApiResponse.success(null);
	}
	
	@GetMapping("access")
	public ApiResponse<List<AccessHistoryDto>> getAccessHistory(
			@RequestParam Optional<LocalDateTime> from,
			@RequestParam Optional<LocalDateTime> to
			) {
		return ApiResponse.success(null);
	}	
}
