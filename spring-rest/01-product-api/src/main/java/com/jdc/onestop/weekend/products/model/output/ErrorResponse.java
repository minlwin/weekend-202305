package com.jdc.onestop.weekend.products.model.output;

import java.util.List;

public record ErrorResponse(
		Type type,
		List<String> messages
		) {

	public enum Type {
		Validation, Business, Platform
	}
}
