package com.jdc.security.demo.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.security.demo.model.form.SignUpForm;
import com.jdc.security.demo.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepo repo;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public int signUp(SignUpForm form) {
		return repo.save(form.entity(passwordEncoder::encode)).getId();
	}
}
