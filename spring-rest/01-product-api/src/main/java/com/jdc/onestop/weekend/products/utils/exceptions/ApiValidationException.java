package com.jdc.onestop.weekend.products.utils.exceptions;

import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> errors;

	public ApiValidationException(List<String> errors) {
		super();
		this.errors = errors;
	}

	public ApiValidationException(BindingResult result) {
		this.errors = result.getFieldErrors().stream().map(a -> a.getDefaultMessage()).toList();
	}

	public List<String> getErrors() {
		return errors;
	}

}
