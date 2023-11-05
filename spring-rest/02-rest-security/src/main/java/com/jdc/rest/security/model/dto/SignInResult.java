package com.jdc.rest.security.model.dto;

public record SignInResult(
		LoginUser user,
		String token
		) {
	
	public SignInResult withUser(LoginUser user) {
		return new SignInResult(user, token);
	}
}
