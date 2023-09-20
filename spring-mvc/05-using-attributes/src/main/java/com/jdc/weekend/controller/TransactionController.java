package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.weekend.model.service.TransactionService;

@Controller
@RequestMapping("transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService service;

	@GetMapping
	String index(ModelMap model) {
		model.put("list", service.findAll());
		return "transaction-list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		model.put("data", service.findById(id));
		return "transaction-details";
	}
}
