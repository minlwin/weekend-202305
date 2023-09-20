package com.jdc.balance.model.dto;

import java.time.LocalDate;

import com.jdc.balance.model.constants.LedgerType;

public record TransactionDto(
		long id, 
		LedgerType type,
		int ledgerId,
		String ledgerName,
		LocalDate issueAt,
		String remark,
		long count,
		long amount
		) {

}
