package com.jdc.rest.security.model.dto;

public record SignInResult(
		LoginUser user,
		String token
		) {

}
