package com.jdc.spring.security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.security.demo.dto.SignUpForm;
import com.jdc.spring.security.demo.service.AccountService;

@Controller
@RequestMapping("signup")
public class SignUpController {
	
	@Autowired
	private AccountService service;

	@GetMapping
	String index() {
		return "signup";
	}
	
	@PostMapping
	String signUp(
			@Validated @ModelAttribute("form")SignUpForm form, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "signup";
		}
		
		service.create(form.account());
		
		return "redirect:/signin";
	}
	
	@ModelAttribute("form")
	SignUpForm form() {
		return new SignUpForm();
	}
}
