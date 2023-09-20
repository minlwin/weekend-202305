package com.jdc.balance.model.dto;

import java.time.LocalDate;

import com.jdc.balance.model.constants.Role;

public record AccountDto(
		int id,
		String name, 
		String email,
		String password,
		Role role,
		LocalDate registAt,
		boolean activated,
		boolean deleted
		) {

}
