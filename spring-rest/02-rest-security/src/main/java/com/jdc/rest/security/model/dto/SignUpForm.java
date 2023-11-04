package com.jdc.rest.security.model.dto;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.rest.security.model.entity.Member;
import com.jdc.rest.security.model.entity.Member.Role;

import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
		@NotBlank(message = "Please enter user name.")
		String name,
		@NotBlank(message = "Please enter login id.")
		String loginid,
		@NotBlank(message = "Please enter password.")
		String password,
		@NotBlank(message = "Please enter email.")
		String email
		) {

	public Member entity(PasswordEncoder encoder) {
		var entity = new Member();
		entity.setName(name);
		entity.setLoginId(loginid);
		entity.setPassword(encoder.encode(password));
		entity.setRole(Role.Member);
		entity.setEmail(email);
		entity.setRegistAt(LocalDateTime.now());
		return entity;
	}

}
