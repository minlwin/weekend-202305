package com.jdc.balance.model.form;

import lombok.Data;

@Data
public class TransactionItemForm {

	private String item;
	private int unitPrice;
	private int quantity;
}
