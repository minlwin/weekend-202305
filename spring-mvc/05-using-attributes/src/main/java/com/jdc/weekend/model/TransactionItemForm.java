package com.jdc.weekend.model;

import java.io.Serializable;

import com.jdc.weekend.model.entity.TransactionItem;

import lombok.Data;

@Data
public class TransactionItemForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String itemName;
	private int unitPrice;
	private int quantity;
	
	public int getTotal() {
		return unitPrice * quantity;
	}
	
	public TransactionItem entity() {
		var entity = new TransactionItem();
		entity.setName(itemName);
		entity.setUnitPrice(unitPrice);
		entity.setQuantity(quantity);
		return entity;
	}
}
