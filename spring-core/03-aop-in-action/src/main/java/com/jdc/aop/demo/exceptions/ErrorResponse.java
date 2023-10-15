package com.jdc.aop.demo.exceptions;

import java.util.List;

public record ErrorResponse(
		Type type,
		List<String> messages
		) {
	public enum Type {
		Validation, Business, Platform
	}
}
