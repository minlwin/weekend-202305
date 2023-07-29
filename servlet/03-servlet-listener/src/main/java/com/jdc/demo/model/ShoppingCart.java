package com.jdc.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Map<Integer, CartItem> cart = Collections.synchronizedMap(new LinkedHashMap<>());
	
	public void addToCart(Product p) {
		var item = cart.get(p.getId());
		
		if(null == item) {
			item = new CartItem(p);
			cart.put(p.getId(), item);
		}
		
		item.countUp();
	}
	
	public void removeFromCart(int productId) {
		cart.remove(productId);
	}
	
	public int getItemCount() {
		return cart.values().stream().mapToInt(a -> a.getQuantity()).sum();
	}
	
	public int getTotal() {
		return cart.values().stream().mapToInt(a -> a.getTotal()).sum();
	}
	
	public List<CartItem> getItems() {
		return new ArrayList<>(cart.values());
	}
	
	public void clear() {
		cart.clear();
	}

}
