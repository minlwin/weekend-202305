package com.jdc.rest.security.utils.security;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenProvider {
	
	private Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	private String issuer = "com.jdc.token.demo";
	private SecretKey signKey = Jwts.SIG.HS512.key().build();
	
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



	public Authentication parse(String authToken) {
		try {
			var jws = Jwts.parser()
					.verifyWith(signKey)
					.requireIssuer(issuer)
					.build()
					.parseSignedClaims(authToken.substring("Bearer : ".length()));
				
				var username = jws.getPayload().getSubject();
				var roles = jws.getPayload().get("role", String.class).split(",");
				var authorities = Arrays.stream(roles)
						.map(a -> new SimpleGrantedAuthority(a)).toList();
				
				return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);			
		} catch (Exception e) {
			logger.info("JWT TOKEN PARSER ERROR : {}", e.getMessage());
		}
		return null;
	}
	
}
