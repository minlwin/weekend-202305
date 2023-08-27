package com.jdc.spring.security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.jdc.spring.security.demo.dto.Account;

@Configuration
public class AdminUserConfiguration {

	@Autowired
	private AccountService service;
	
	@EventListener(value = ContextRefreshedEvent.class)
	public void initAdmin() {
		
		if(service.count() == 0L) {
			Account admin = Account.withDefault()
					.name("Admin User")
					.email("admin@gmail.com")
					.password("admin")
					.role("Admin");
			
			service.create(admin);
		}
	}
}
