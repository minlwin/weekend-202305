package com.jdc.rest.security.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ApiResponse<T>(
		boolean success,
		T payload,
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		LocalDateTime issueAt
		) {

	public static<T> ApiResponse<T> success(T payload) {
		return new ApiResponse<T>(true, payload, LocalDateTime.now());
	}

	public static<T> ApiResponse<T> fails(T payload) {
		return new ApiResponse<T>(false, payload, LocalDateTime.now());
	}
}
