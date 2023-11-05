package com.jdc.rest.security.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.rest.security.model.repo.MemberRepo;
import com.jdc.rest.security.utils.exception.ApiBusinessException;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepo memberRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepo.findById(username)
			.map(a -> User.withUsername(username)
					.password(a.getPassword())
					.authorities(a.getRole().name())
					.build())
			.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
	}

}
