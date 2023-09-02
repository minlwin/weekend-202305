package com.jdc.balance.model.form;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class TransactionForm {

	private String username;
	private int ledgerId;
	private LocalDate issueAt;
	private String remark;
	private boolean deleted;
	private List<TransactionItemForm> items;
}
