package com.jdc.balance.web.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.form.LedgerForm;

@Controller
@RequestMapping("member/ledger")
public class LedgerController {

	@GetMapping
	String index(@RequestParam Optional<LedgerType> type) {
		return "member/ledgers";
	}

	@PostMapping
	String save(@ModelAttribute("form") LedgerForm form) {
		System.out.println(form);
		return "redirect:/member/ledger";
	}

	@ModelAttribute("pageTitle")
	String pageTitle() {
		return "Ledger";
	}

	@ModelAttribute("form")
	LedgerForm form() {
		return new LedgerForm();
	}

}
