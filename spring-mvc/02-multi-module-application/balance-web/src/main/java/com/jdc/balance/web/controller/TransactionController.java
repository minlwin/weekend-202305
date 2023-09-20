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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.service.TransactionService;

@Controller
@RequestMapping("member/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;

	@GetMapping("{type}")
	String search(
			Authentication authentication,
			@PathVariable LedgerType type, 
			@RequestParam Optional<String> ledgerName, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to,
			ModelMap model) {
		model.put("list", service.search(authentication.getName(), Optional.of(type), ledgerName, from, to));
		return "member/transactions";
	}
	
	@GetMapping("{type}/{id}")
	String showDetails(@PathVariable long id, ModelMap model) {
		model.put("data", service.findById(id).orElseThrow());
		return "member/transaction-details";
	}
		
	@ModelAttribute
	void pageTitle(@PathVariable LedgerType type, ModelMap model) {
		model.put("pageTitle", type.name());
		model.put("type", type);
	}

}
