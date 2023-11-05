package com.jdc.rest.security.model.dto;

public record ProfileDto(
		MemberDto member,
		long accessTimes
		) {
}
