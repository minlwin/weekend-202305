package com.jdc.balance.model.dto;

import java.time.LocalDate;
import java.util.List;

public record TransactionDetailsDto(
		TransactionDto transaction,
		List<TransactionItemDto> items
		) {

	public int total() {
		return (null == items || items.isEmpty()) ? 0 :
			items.stream().mapToInt(a -> a.total()).sum();
	}
	
	public String getLedgerName() {
		return transaction.ledgerName();
	}
	
	public LocalDate getIssueAt() {
		return transaction.issueAt();
	}
	
	public String getRemark() {
		return transaction.remark();
	}
	
	public int getAllTotal() {
		return items.stream().mapToInt(a -> a.unitPrice() * a.quantity()).sum();
	}
}
