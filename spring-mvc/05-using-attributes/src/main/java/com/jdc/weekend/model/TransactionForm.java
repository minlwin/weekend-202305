package com.jdc.weekend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jdc.weekend.model.entity.Transaction;

import lombok.Data;

@Data
public class TransactionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private List<TransactionItemForm> items = new ArrayList<>();
	
	public void addItem(TransactionItemForm item) {
		items.add(item);
	}
	
	public int getAllTotal() {
		return items.stream().mapToInt(a -> a.getTotal()).sum();
	}

	public Transaction entity() {
		var entity = new Transaction();
		entity.setTitle(title);
		items.stream().map(a -> a.entity()).forEach(entity::addItem);
		return entity;
	}
}
