package com.jdc.weekend.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String title;
	
	@OneToMany(
			mappedBy = "transaction", 
			orphanRemoval = true,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<TransactionItem> items = new ArrayList<>();
	
	public void addItem(TransactionItem item) {
		item.setTransaction(this);
		items.add(item);
	}
	
	public int getAllTotal() {
		return items.stream().mapToInt(a -> a.getUnitPrice() * a.getQuantity()).sum();
	}
}
