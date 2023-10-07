package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(columnDefinition = "tinyint(1) default '0'")
	private Boolean isDeleted;
	@OneToMany(mappedBy = "category",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
	private List<Product> products = new ArrayList<Product>() ;
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public void addProduct(Product product) {
		product.setCategory(this);
		this.products.add(product);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
