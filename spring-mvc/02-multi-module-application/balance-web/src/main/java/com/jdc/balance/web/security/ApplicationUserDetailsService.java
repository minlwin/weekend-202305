package com.jdc.balance.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.balance.model.service.AccountService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return service.findByEmail(username)
				.map(a -> User.withUsername(username)
						.password(a.password())
						.authorities(a.role().name())
						.disabled(!a.activated())
						.accountExpired(a.deleted())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
