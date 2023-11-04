package com.jdc.rest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.rest.security.model.dto.SignInForm;
import com.jdc.rest.security.model.dto.SignInResult;
import com.jdc.rest.security.model.dto.SignUpForm;

@Service
public class SecurityService {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Transactional(readOnly = true)
	public SignInResult signIn(SignInForm form) {
		
		// Authenticate with Authentication Manager
		var result = authenticationManager.authenticate(form.authentication());
		
		// Generate JWT Token
		var jwtToken = tokenProvider.generate(result);
		
		// Get Login User with member service
		var loginUser = memberService.findByLoginId(result.getName());

		// Create and return result
		
		return new SignInResult(loginUser, jwtToken);
	}

	@Transactional
	public String createUser(SignUpForm form) {
		return memberService.createUser(form);
	}

}
