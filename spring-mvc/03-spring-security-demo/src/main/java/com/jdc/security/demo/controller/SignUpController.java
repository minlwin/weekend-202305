package com.jdc.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.security.demo.model.form.SignUpForm;
import com.jdc.security.demo.model.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("signup")
@RequiredArgsConstructor
public class SignUpController {
	
	private final AccountService service;

	@GetMapping
	String index() {
		return "signup";
	}
	
	@PostMapping
	String signUp(@Validated @ModelAttribute("form") SignUpForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "signup";
		}
		
		service.signUp(form);
		
		return "redirect:/signin";
	}
	
	@ModelAttribute("form")
	SignUpForm form() {
		return new SignUpForm();
	}
}
