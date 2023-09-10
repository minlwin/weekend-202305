package com.jdc.balance.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionItemForm {

	private String item;
	private int unitPrice;
	private int quantity;
}
