package com.jdc.rest.security.service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenProvider {
	
	private String issuer = "com.jdc.token.demo";
	private Key signKey = Jwts.SIG.HS512.key().build();
	
	

	public String generate(Authentication result) {
		
		if(null != result && result.isAuthenticated()) {
			
			var issueAt = new Date();
			var calendar = Calendar.getInstance();
			calendar.setTime(issueAt);
			calendar.add(Calendar.MINUTE, 5);
			String role = result.getAuthorities()
					.stream().map(a -> a.getAuthority())
					.collect(Collectors.joining(","));
			
			return Jwts.builder().issuer(issuer)
				.issuedAt(issueAt)
				.expiration(calendar.getTime())
				.subject(result.getName())
				.claim("role", role)
				.signWith(signKey).compact();
			
		}
		return null;
	}
	
}
