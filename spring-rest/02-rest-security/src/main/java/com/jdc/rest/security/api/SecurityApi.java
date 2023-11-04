package com.jdc.rest.security.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.rest.security.model.dto.ApiResponse;
import com.jdc.rest.security.model.dto.SignInForm;
import com.jdc.rest.security.model.dto.SignInResult;
import com.jdc.rest.security.model.dto.SignUpForm;
import com.jdc.rest.security.model.dto.SignUpResult;

@RestController
@RequestMapping("security")
public class SecurityApi {

	@PostMapping("signup")
	public ApiResponse<SignUpResult> signUp(SignUpForm form) {
		return ApiResponse.success(null);
	}
	
	@PostMapping("signin")
	public ApiResponse<SignInResult> signIn(SignInForm form) {
		return ApiResponse.success(null);
	}
}
