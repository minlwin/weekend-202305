package com.jdc.balance.model.dto;

import java.util.List;

public record TransactionDetailsDto(
		TransactionDto transaction,
		List<TransactionItemDto> items
		) {

	public int total() {
		return (null == items || items.isEmpty()) ? 0 :
			items.stream().mapToInt(a -> a.total()).sum();
	}
}
