package com.jdc.spring.security.demo.dto;

import java.util.Map;
import java.util.function.Function;

public record Account(
		Integer id,
		String name,
		String email,
		String password,
		String role,
		boolean activated,
		boolean deleted
		) {

	public Account role(String role) {
		return new Account(id, name, email, password, role, activated, deleted);
	}
	
	public Account password(String password) {
		return new Account(id, name, email, password, role, activated, deleted);
	}
	
	public Account email(String email) {
		return new Account(id, name, email, password, role, activated, deleted);
	}
	
	public Account name(String name) {
		return new Account(id, name, email, password, role, activated, deleted);
	}
	
	public static Account withDefault() {
		return new Account(null, null, null, null, null, true, false);
	}
	
	public Map<String, Object> insertParam(Function<String, String> encoder) {
		return Map.of(
				"name", name,
				"email", email,
				"password", encoder.apply(password),
				"role", role
				);
	}
}
