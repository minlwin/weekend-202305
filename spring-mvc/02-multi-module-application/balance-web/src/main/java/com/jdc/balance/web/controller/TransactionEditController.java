package com.jdc.balance.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.LedgerDto;
import com.jdc.balance.model.dto.TransactionDetailsDto;
import com.jdc.balance.model.form.TransactionForm;
import com.jdc.balance.model.form.TransactionItemForm;
import com.jdc.balance.model.service.LedgerService;
import com.jdc.balance.model.service.TransactionService;

@Controller
@RequestMapping("member/transaction")
@SessionAttributes("form")
public class TransactionEditController {
	
	@Autowired
	private LedgerService ledgerService;
	@Autowired
	private TransactionService service;

	@GetMapping("{type}/edit")
	String edit(@PathVariable LedgerType type, 
			@RequestParam Optional<Long> id) {
		return "member/transaction-edit";
	}
	
	@PostMapping("{type}/edit")
	String save(@PathVariable LedgerType type,
			@RequestParam Optional<Long> id, 
			@Validated @ModelAttribute("form") TransactionForm form, 
			BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			return "member/transaction-edit";
		}
		
		TransactionDetailsDto dto = null;
				
		if(id.filter(a -> a > 0).isEmpty()) {
			dto = service.create(form);
		} else {
			dto = service.update(id.get(), form);
		}
		
		model.put("form", new TransactionForm());
		
		return "redirect:/member/transaction/%s/%d".formatted(type.name(), dto.transaction().id());
	}
	
	@PostMapping("{type}/edit/add-item")
	String addItem(@PathVariable LedgerType type, 
			@ModelAttribute("form") TransactionForm form, 
			@Validated @ModelAttribute("itemForm") TransactionItemForm itemForm, 
			BindingResult result
			) {
		
		if(result.hasErrors()) {
			return "member/transaction-edit";
		}
		
		form.addItem(itemForm);
		
		return "redirect:/member/transaction/%s/edit".formatted(type.name());
	}
	
	@ModelAttribute
	void pageTitle(@PathVariable LedgerType type, ModelMap model) {
		model.put("pageTitle", "Edit %s".formatted(type.name()));
		model.put("type", type);
	}
	
	@ModelAttribute("ledgers")
	public List<LedgerDto> ledgers(Authentication authentication, @PathVariable LedgerType type) {
		return ledgerService.search(authentication.getName(), Optional.of(type), Optional.empty(), Optional.of(false));
	}
	
	@ModelAttribute("form")
	public TransactionForm form() {
		var form = new TransactionForm();
		form.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return form;
	}
	
	@ModelAttribute("itemForm")
	public TransactionItemForm itemForm() {
		return new TransactionItemForm();
	}
	
}
