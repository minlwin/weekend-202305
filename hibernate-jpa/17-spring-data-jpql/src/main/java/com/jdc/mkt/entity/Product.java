package com.jdc.mkt.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findByPrice",query = "select p from Product p where p.price >= :gt_price and p.price <= :lt_price")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@Column(name="p_name")
	private String name;
	private int price;
	@Enumerated(EnumType.STRING)
	private Size size;
	@Column(columnDefinition = "tinyint(1) default '0'")
	private boolean isDeleted;
	@ManyToOne
	private Category category;
	
	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
		
	}


	public  enum Size {
		SMALL,MEDIUM,LARGE
	}
}
