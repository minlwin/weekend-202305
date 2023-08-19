package com.jdc.balance.web.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.model.constants.LedgerType;

@Controller
@RequestMapping("member/transaction")
public class TransactionController {

	@GetMapping("{type}")
	String search(@PathVariable LedgerType type, 
			@RequestParam Optional<String> ledgerName, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to,
			ModelMap model) {
		return "member/transactions";
	}
	
	@GetMapping("details/{id}")
	String showDetails(@PathVariable long id, ModelMap model) {
		return "member/transacton-details";
	}
	
	@GetMapping("edit/{type}")
	String edit(@RequestParam Optional<Long> id) {
		return "member/transacton-edit";
	}
	
	String save() {
		return null;
	}
	
	@ModelAttribute("pageTitle")
	String pageTitle(@PathVariable LedgerType type) {
		return type.name();
	}

}
