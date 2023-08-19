package com.jdc.mkt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "product_tbl")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductPk.class)
public class Product  implements Serializable{

	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private ProductPk id;
	@Id
	private String code;
	@Id
	private String size; 
	
	private String name;
	private int price;
	@ManyToOne
	private Category category;
	
	public Product(String name, int price, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	
}
