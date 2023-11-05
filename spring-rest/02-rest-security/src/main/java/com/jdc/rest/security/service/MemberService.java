package com.jdc.rest.security.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.rest.security.model.dto.MemberDto;
import com.jdc.rest.security.model.dto.ProfileDto;
import com.jdc.rest.security.model.dto.SignUpForm;
import com.jdc.rest.security.model.entity.Member;
import com.jdc.rest.security.model.repo.AccessHistoryRepo;
import com.jdc.rest.security.model.repo.MemberRepo;
import com.jdc.rest.security.utils.exception.ApiBusinessException;

@Service
@Transactional(readOnly = true)
public class MemberService {
	
	@Autowired
	private MemberRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccessHistoryRepo accessHistoryRepo;

	@Transactional
	public String createUser(SignUpForm form) {
		repo.save(form.entity(passwordEncoder));
		return "Member has been created successfully!";
	}
	
	public MemberDto findByLoginId(String loginId) {
		
		return repo.findById(loginId)
				.map(MemberDto::withMember)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
		
	}

	public ProfileDto getProfile(String username) {
		var memberDto = repo.findById(username)
				.map(MemberDto::withMember)
				.orElseThrow(() -> new ApiBusinessException("Invalid member id."));
		
		var accessTimes = accessHistoryRepo.countByIdMemberId(username);
		
		return new ProfileDto(memberDto, accessTimes);
	}

	public List<MemberDto> search(Optional<String> name, Optional<LocalDate> from,
			Optional<LocalDate> to) {
		
		return repo.findAll(
			withName(name).and(withFrom(from).and(withTo(to)))
		).stream().map(MemberDto::withMember).toList();
	}
	
	private Specification<Member> withName(Optional<String> param) {
		return param.filter(StringUtils::hasLength).isEmpty() ? Specification.where(null) 
				// (lower(m.loginId) like ? or lower(m.name) like ?) 
				:(root, query, cb) -> cb.or(
						cb.like(cb.lower(root.get("loginId")), param.get().toLowerCase().concat("%")),
						cb.like(cb.lower(root.get("name")), param.get().toLowerCase().concat("%"))				
						);
	}

	private Specification<Member> withFrom(Optional<LocalDate> param) {
		return param.isEmpty() ? Specification.where(null) 
				// (lower(m.loginId) like ? or lower(m.name) like ?) 
				:(root, query, cb) -> cb.greaterThanOrEqualTo(root.get("registAt"), param.get().atStartOfDay());
	}
	
	private Specification<Member> withTo(Optional<LocalDate> param) {
		return param.isEmpty() ? Specification.where(null) 
				// (lower(m.loginId) like ? or lower(m.name) like ?) 
				:(root, query, cb) -> cb.lessThan(root.get("registAt"), param.get().plusDays(1).atStartOfDay());
	}

}
