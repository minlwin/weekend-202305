package com.jdc.spring.security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return service.findByEmail(username)
				.map(a -> User.withUsername(username)
						.password(a.password())
						.authorities(a.role())
						.disabled(!a.activated())
						.accountExpired(a.deleted())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
