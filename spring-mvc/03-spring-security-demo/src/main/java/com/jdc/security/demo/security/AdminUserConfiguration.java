package com.jdc.security.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.security.demo.model.entity.Account;
import com.jdc.security.demo.model.entity.Account.Role;
import com.jdc.security.demo.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdminUserConfiguration {
	
	private final AccountRepo repo;
	private final PasswordEncoder encoder;

	@Transactional
	@EventListener(value = ContextRefreshedEvent.class)
	public void configureAdminUser() {
		
		if(repo.count() == 0L) {
			var admin = new Account();
			admin.setName("Admin User");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(encoder.encode("admin"));
			admin.setRole(Role.Admin);
			admin.setActivated(true);
			
			repo.save(admin);
		}
	}
}
