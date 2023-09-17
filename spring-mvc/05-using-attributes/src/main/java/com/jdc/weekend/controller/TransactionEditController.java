package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.weekend.model.TransactionForm;
import com.jdc.weekend.model.TransactionItemForm;
import com.jdc.weekend.model.service.TransactionService;

@Controller
@RequestMapping("transaction/edit")
@SessionAttributes("transactionForm")
public class TransactionEditController {
	
	@Autowired
	private TransactionService service;
	
	@GetMapping
	String index() {
		return "transaction-edit";
	}

	@PostMapping
	String saveTransaction(@ModelAttribute("transactionForm") TransactionForm form, ModelMap model, RedirectAttributes redirect) {
		int id = service.save(form);
		model.put("transactionForm", new TransactionForm());
		redirect.addFlashAttribute("message", "Transaction has been created successfully.");
		return "redirect:/transactions/%d".formatted(id);
	}

	@PostMapping("add-item")
	String addItem(
			@ModelAttribute("itemForm") TransactionItemForm item, 
			@ModelAttribute("transactionForm") TransactionForm form) {
		form.addItem(item);
		return "redirect:/transaction/edit";
	}
	
	@ModelAttribute("itemForm")
	TransactionItemForm itemForm() {
		return new TransactionItemForm();
	}
	
	@ModelAttribute("transactionForm")
	TransactionForm form() {
		return new TransactionForm();
	}

}
