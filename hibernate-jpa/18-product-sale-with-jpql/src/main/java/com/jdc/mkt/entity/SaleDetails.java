package com.jdc.mkt.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "sale_details")
public class SaleDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private SaleDetailsPk id;
	private int qty;	
	@ManyToOne
	@MapsId("productId")
	private Product product;	
	@ManyToOne
	@MapsId("salesId")
	private Sales sales;
	private int total;
	
		
	public SaleDetails(int qty, Product product) {
		super();
		this.qty = qty;
		this.product = product;
		
	}
	
	
}
