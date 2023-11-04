package com.jdc.rest.security.model.dto;

import java.time.LocalDateTime;

import com.jdc.rest.security.model.entity.Member;
import com.jdc.rest.security.model.entity.Member.Role;
import com.jdc.rest.security.model.entity.MemberAccess;

public record LoginUser(
		String name,
		Role role,
		String email,
		LocalDateTime registAt,
		LocalDateTime firstLogin,
		LocalDateTime lastLogin
		) {

	public static LoginUser withMember(Member member) {
		return new LoginUser(member.getName(), member.getRole(), member.getEmail(), member.getRegistAt(), null, null);
	}

	public static LoginUser withMemberAndAccess(Member member, MemberAccess access) {
		return new LoginUser(member.getName(), member.getRole(), member.getEmail(), member.getRegistAt(), access.getFirstLoginAt(), access.getLastLoginAt());
	}

}
