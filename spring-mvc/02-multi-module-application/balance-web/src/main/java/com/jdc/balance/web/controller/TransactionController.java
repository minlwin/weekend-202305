package com.jdc.balance.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member/transaction")
public class TransactionController {

	@GetMapping("{type}")
	String search(@PathVariable String type, ModelMap model) {
		return "member/transactions";
	}
	
	@ModelAttribute("pageTitle")
	String pageTitle(@PathVariable String type) {
		return type;
	}
	
}
