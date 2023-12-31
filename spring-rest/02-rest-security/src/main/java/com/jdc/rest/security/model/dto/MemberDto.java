package com.jdc.rest.security.model.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.jdc.rest.security.model.entity.Member;
import com.jdc.rest.security.model.entity.Member.Role;

public record MemberDto(
		String name,
		Role role,
		String email,
		LocalDateTime registAt,
		LocalDateTime firstLogin,
		LocalDateTime lastLogin
		) {

	public static MemberDto withMember(Member member) {
		var access = Optional.ofNullable(member.getAccess());
		return new MemberDto(
				member.getName(), 
				member.getRole(), 
				member.getEmail(), 
				member.getRegistAt(), 
				access.map(a -> a.getFirstLoginAt()).orElse(null), 
				access.map(a -> a.getLastLoginAt()).orElse(null));
	}

}
