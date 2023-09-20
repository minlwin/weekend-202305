package com.jdc.balance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.web.model.MemberSignUpForm;

@Controller
@RequestMapping("signup")
public class SignUpController {
	
	@Autowired
	private AccountService service;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	String index() {
		return "signup";
	}
	
	@PostMapping
	String signUp(
			@Validated @ModelAttribute("form") MemberSignUpForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "signup";
		}
		
		service.create(form.account(passwordEncoder));
		
		return "redirect:/signin";
	}
	
	@ModelAttribute("form")
	MemberSignUpForm form() {
		return new MemberSignUpForm();
	}
}
