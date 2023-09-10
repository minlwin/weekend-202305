package com.jdc.balance.web.security;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.form.AccountForm;
import com.jdc.balance.model.service.AccountService;

@Service
public class AdminUserInitializer {

	@Autowired
	private AccountService service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(value = ContextRefreshedEvent.class)
	public void initializeAdmin() {
		
		if(service.getCount() == 0L) {
			
			var admin = new AccountForm();
			admin.setName("Admin User");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRole(Role.Admin);
			admin.setRegistAt(LocalDate.now());
			admin.setActivated(true);
			admin.setDeleted(false);
			service.create(admin);
			
		}
	}
}
