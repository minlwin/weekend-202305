package com.jdc.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartItem {

	private final Product product;
	private int quantity;
	
	public int getTotal() {
		return quantity * product.getPrice();
	}

	public void countUp() {
		
		synchronized (product) {
			quantity ++;
		}
	}
}
