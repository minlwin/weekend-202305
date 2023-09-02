package com.jdc.balance.model.dto;

public record BalanceDto(
		TransactionDto transaction,
		long balance
		) {

}
