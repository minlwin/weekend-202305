package com.jdc.balance.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member/ledger")
public class LedgerController {
	
	@GetMapping
	String index() {
		return "member/ledgers";
	}

	@ModelAttribute("pageTitle")
	String pageTitle() {
		return "Ledger";
	}

}
