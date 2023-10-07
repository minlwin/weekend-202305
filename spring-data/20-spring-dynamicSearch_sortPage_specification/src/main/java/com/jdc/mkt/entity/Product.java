package com.jdc.mkt.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false,columnDefinition = "varchar(30) not null check(char_length(name)>= 4)")
	private String name;
	@Column(name = "dt_price")
	private Integer detailPrice;
	@Column(name = "ws_price")
	private Integer wholeSalePrice;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;
	
	public Product(String name, int detailPrice, int wholeSalePrice, Category category) {
		super();
		this.name = name;
		this.detailPrice = detailPrice;
		this.wholeSalePrice = wholeSalePrice; 
		this.category = category;
	}

}
