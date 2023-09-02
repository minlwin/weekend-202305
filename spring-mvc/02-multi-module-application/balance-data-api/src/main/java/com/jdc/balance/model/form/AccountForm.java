package com.jdc.balance.model.form;

import java.time.LocalDate;

import com.jdc.balance.model.constants.Role;

import lombok.Data;

@Data
public class AccountForm {
	private String name;
	private String email;
	private Role role;
	private LocalDate registAt;
	private boolean activated;
	private boolean deleted;
}
