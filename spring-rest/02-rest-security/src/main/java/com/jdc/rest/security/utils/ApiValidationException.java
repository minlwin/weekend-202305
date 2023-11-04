package com.jdc.rest.security.utils;

import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private List<String> messages;
	
	public ApiValidationException(BindingResult result) {
		messages = result.getFieldErrors().stream()
			.map(a -> a.getDefaultMessage()).toList();
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
