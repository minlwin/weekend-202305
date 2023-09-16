package com.jdc.balance.web.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApplicationAuthSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		var role = authentication.getAuthorities().stream().map(a -> a.getAuthority()).findAny().get();
		response.sendRedirect("%s/%s/home".formatted(
				request.getContextPath(),
				role.toLowerCase()));
	}

}
