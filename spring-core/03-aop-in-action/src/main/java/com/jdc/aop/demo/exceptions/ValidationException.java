package com.jdc.aop.demo.exceptions;

import java.util.List;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<String> messages;
	
	public ValidationException(BindingResult result) {
		messages = result.getFieldErrors().stream()
				.map(a -> a.getDefaultMessage()).toList();
	}
	
	public List<String> getMessages() {
		return messages;
	}

}
