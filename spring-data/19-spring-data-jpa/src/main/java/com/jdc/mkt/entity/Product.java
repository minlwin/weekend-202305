package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@NamedNativeQuery(
		name = "selectAllProductByName",
		query = "select * from product where lower(name) like lower(?)",
		resultClass = Product.class)
@NamedQuery(
		name = "selectByCategoryNameAndPrice",
		query="""
				select p from Product p 
				where p.category.name = :name 
				and p.detailPrice <= :price
				"""
		)
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
	@OneToMany(mappedBy = "product",orphanRemoval = true)
	private List<SaleDetails> saleDetails = new ArrayList<SaleDetails>();

	public Product(String name, int detailPrice, int wholeSalePrice, Category category) {
		super();
		this.name = name;
		this.detailPrice = detailPrice;
		this.wholeSalePrice = wholeSalePrice; 
		this.category = category;
	}

}
