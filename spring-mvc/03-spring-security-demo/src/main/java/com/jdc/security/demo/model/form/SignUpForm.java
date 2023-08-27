package com.jdc.security.demo.model.form;

import java.util.function.Function;

import com.jdc.security.demo.model.entity.Account;
import com.jdc.security.demo.model.entity.Account.Role;

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
	
	public Account entity(Function<String, String> encoder) {
		var member = new Account();
		member.setName(name);
		member.setEmail(email);
		member.setPassword(encoder.apply(password));
		member.setRole(Role.Member);
		member.setActivated(true);
		return member;
	}
}
