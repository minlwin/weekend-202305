package com.jdc.balance.web.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.model.service.TransactionService;

@Controller
@RequestMapping("member/balance")
public class BalanceController {
	
	@Autowired
	private TransactionService service;

	@GetMapping
	String search(Authentication authentication, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to,
			ModelMap model) {
		model.put("list", service.searchBalance(authentication.getName(), from, to));
		return "member/balances";
	}
	
	@ModelAttribute("pageTitle")
	String pageTitle() {
		return "Balance";
	}
		
}
