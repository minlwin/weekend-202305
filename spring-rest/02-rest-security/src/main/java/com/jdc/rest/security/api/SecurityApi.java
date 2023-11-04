package com.jdc.rest.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.rest.security.model.dto.ApiResponse;
import com.jdc.rest.security.model.dto.SignInForm;
import com.jdc.rest.security.model.dto.SignInResult;
import com.jdc.rest.security.model.dto.SignUpForm;
import com.jdc.rest.security.service.SecurityService;
import com.jdc.rest.security.utils.ApiValidationException;

@RestController
@RequestMapping("security")
public class SecurityApi {
	
	@Autowired
	private SecurityService service;

	@PostMapping("signup")
	public ApiResponse<String> signUp(
			@Validated SignUpForm form, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ApiValidationException(result);
		}
		
		return ApiResponse.success(service.createUser(form));
	}
	
	@PostMapping("signin")
	public ApiResponse<SignInResult> signIn(
			@Validated SignInForm form,
			BindingResult result) {
		if(result.hasErrors()) {
			throw new ApiValidationException(result);
		}
		return ApiResponse.success(service.signIn(form));
	}
}
