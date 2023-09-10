package com.jdc.balance.web.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.form.AccountForm;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberSignUpForm {

	@NotBlank(message = "Please enter your name.")
	private String name;
	@Email(message = "Please enter valid email address.")
	@NotBlank(message = "Please enter email for login.")
	private String email;
	@Length(min = 4, max = 8, message = "Password must be within 4 to 8 digits.")
	@NotBlank(message = "Please enter password for login.")
	private String password;
	
	public AccountForm account(PasswordEncoder encoder) {
		var form = new AccountForm();
		form.setActivated(true);
		form.setDeleted(false);
		form.setEmail(email);
		form.setName(name);
		form.setPassword(encoder.encode(password));
		form.setRole(Role.Member);
		form.setRegistAt(LocalDate.now());
		return form;
	}
}
