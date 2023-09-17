package com.jdc.balance.model.dto;

import java.time.LocalDate;

import com.jdc.balance.model.constants.LedgerType;

public record BalanceDto(
		TransactionDto transaction,
		long balance
		) {
	
	public long getId() {
		return transaction.id();
	}
	
	public LocalDate getIssueAt() {
		return transaction.issueAt();
	}

	public LedgerType getType() {
		return transaction.type();
	}
	
	public String getRemark() {
		return transaction.remark();
	}
	
	public String getLedgerName() {
		return transaction.ledgerName();
	}
	
	public long getCredit() {
		return getType() == LedgerType.Credit ? transaction.amount() : 0;
	}

	public long getDebit() {
		return getType() == LedgerType.Debit ? transaction.amount() : 0;
	}
}
