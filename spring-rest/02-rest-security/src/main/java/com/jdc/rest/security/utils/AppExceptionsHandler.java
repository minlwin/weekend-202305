package com.jdc.rest.security.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.rest.security.model.dto.ApiResponse;

@RestControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiResponse<List<String>> handle(ApiValidationException e) {
		return ApiResponse.fails(e.getMessages());
	}
}
