package com.jdc.balance.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.service.AccountService;

@Controller
@RequestMapping("admin/home")
public class AdminController {
	
	@Autowired
	private AccountService service;

	@GetMapping
	String index(
			ModelMap model,
			@RequestParam Optional<Role> role, 
			@RequestParam Optional<String> name) {
		
		model.put("list", service.search(role, name, Optional.of(false)));
		return "admin/home";
	}
}
