package com.jdc.rest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.rest.security.model.dto.LoginUser;
import com.jdc.rest.security.model.dto.SignUpForm;
import com.jdc.rest.security.model.repo.MemberRepo;
import com.jdc.rest.security.utils.exception.ApiBusinessException;

@Service
@Transactional(readOnly = true)
public class MemberService {
	
	@Autowired
	private MemberRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public String createUser(SignUpForm form) {
		repo.save(form.entity(passwordEncoder));
		return "Member has been created successfully!";
	}
	
	public LoginUser findByLoginId(String loginId) {
		
		var member = repo.findById(loginId)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
		
		if(null == member.getAccess()) {
			return LoginUser.withMember(member);
		}
		
		return LoginUser.withMemberAndAccess(member, member.getAccess());
	}

}
