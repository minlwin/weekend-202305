package com.jdc.rest.security.model.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(
		boolean success,
		T payload,
		LocalDateTime issueAt
		) {

	public static<T> ApiResponse<T> success(T payload) {
		return new ApiResponse<T>(true, payload, LocalDateTime.now());
	}

	public static<T> ApiResponse<T> fails(T payload) {
		return new ApiResponse<T>(false, payload, LocalDateTime.now());
	}
}
