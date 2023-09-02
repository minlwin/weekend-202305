package com.jdc.balance.model.dto;

public record TransactionItemDto(
		String item,
		int unitPrice,
		int quantity
		) {
	public int total() {
		return unitPrice * quantity;
	}
}
