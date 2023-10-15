package com.jdc.onestop.weekend.products.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ProductHistoryPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "product_id")
	private int productId;

	private int version;
}
