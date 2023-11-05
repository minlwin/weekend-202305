package com.jdc.rest.security.model.dto;

public record SignInResult(
		String loginId,
		MemberDto user,
		String token
		) {
	
	public SignInResult withUser(String loginId, MemberDto user) {
		return new SignInResult(loginId, user, token);
	}
}
