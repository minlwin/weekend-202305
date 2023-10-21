package com.jdc.onestop.weekend.products.utils.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.onestop.weekend.products.model.output.ErrorResponse;
import com.jdc.onestop.weekend.products.model.output.ErrorResponse.Type;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	ErrorResponse handle(ApiBusinessException e) {
		return new ErrorResponse(Type.Business, List.of(e.getMessage()));
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	ErrorResponse handle(ApiValidationException e) {
		return new ErrorResponse(Type.Validation, e.getErrors());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorResponse handle(Exception e) {
		return new ErrorResponse(Type.Platform, List.of(e.getMessage()));
	}
	
}
