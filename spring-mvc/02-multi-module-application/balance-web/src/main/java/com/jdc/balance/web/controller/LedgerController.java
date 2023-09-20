package com.jdc.balance.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.service.LedgerService;

@Controller
@RequestMapping("member/ledger")
public class LedgerController {
	
	@Autowired
	private LedgerService service;

	@GetMapping
	String index(ModelMap model, @RequestParam Optional<LedgerType> type) {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		model.put("active", type.isEmpty() ? "All" : type.get().name());
		model.put("list", service.search(auth.getName(), type, Optional.empty(), Optional.of(false)));
		return "member/ledgers";
	}

	@PostMapping
	String save(
			ModelMap model,
			@RequestParam Optional<LedgerType> searchType,
			@RequestParam Optional<Integer> id,
			@Validated @ModelAttribute("form") LedgerForm form, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			var auth = SecurityContextHolder.getContext().getAuthentication();
			model.put("active", searchType.isEmpty() ? "All" : searchType.get().name());
			model.put("list", service.search(auth.getName(), searchType, Optional.empty(), Optional.of(false)));
			model.put("errors", true);
			return "member/ledgers";
		}
		
		if(id.filter(a -> a > 0).isPresent()) {
			service.update(id.get(), form);
		} else {
			service.create(form);
		}
		
		return "redirect:/member/ledger";
	}

	@ModelAttribute("pageTitle")
	String pageTitle() {
		return "Ledger";
	}

	@ModelAttribute("form")
	LedgerForm form() {
		var form = new LedgerForm();
		form.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return form;
	}

}
