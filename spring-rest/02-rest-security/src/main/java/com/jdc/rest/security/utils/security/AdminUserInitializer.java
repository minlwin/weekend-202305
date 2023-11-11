package com.jdc.rest.security.utils.security;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.rest.security.model.entity.Member;
import com.jdc.rest.security.model.entity.MemberAccess;
import com.jdc.rest.security.model.entity.Member.Role;
import com.jdc.rest.security.model.repo.MemberRepo;

@Configuration
public class AdminUserInitializer {

	@Autowired
	private MemberRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(value = ContextRefreshedEvent.class)
	public void createAdminUser() {
		if(repo.count() == 0) {
			var entity = new Member();
			entity.setName("Admin User");
			entity.setLoginId("admin");
			entity.setPassword(passwordEncoder.encode("adminpwd"));
			entity.setRole(Role.Admin);
			entity.setEmail("admin@gmail.com");
			entity.setRegistAt(LocalDateTime.now());
			
			var access = new MemberAccess();
			access.setMember(entity);
			entity.setAccess(access);
			
			repo.save(entity);
		}
	}
}
