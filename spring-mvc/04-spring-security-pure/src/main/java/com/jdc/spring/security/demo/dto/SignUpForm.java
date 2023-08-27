package com.jdc.spring.security.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Please enter your name.")
	private String name;
	
	@NotBlank(message = "Please enter login email.")
	@Email(message = "Please enter valid email.")
	private String email;
	
	@NotBlank(message = "Please enter login password.")
	private String password;
	
	public Account account() {
		return Account.withDefault()
				.name(name)
				.email(email)
				.password(password)
				.role("Member");
	}
}
