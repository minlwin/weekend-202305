package com.jdc.balance.model.dto;

import com.jdc.balance.model.constants.LedgerType;

public record LedgerDto(
		int id, 
		String name,
		LedgerType type,
		long transactionCount,
		long transactionAmount
		) {

}
