package com.jdc.security.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.security.demo.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService{
	
	private final AccountRepo repo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findOneByEmail(username)
				.map(a -> User.withUsername(username)
						.password(a.getPassword())
						.authorities(a.getRole().name())
						.disabled(!a.isActivated())
						.accountExpired(a.isDeleted())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
