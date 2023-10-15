package com.jdc.aop.demo.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.aop.demo.exceptions.ErrorResponse.Type;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	ErrorResponse handle(ValidationException e) {
		return new ErrorResponse(Type.Validation, e.getMessages());
	}
}
