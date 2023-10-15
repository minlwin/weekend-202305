package com.jdc.onestop.weekend.products.model.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	private int price;
	private String description;
	@ManyToMany
	private List<Category> categories = new ArrayList<>();
	@ElementCollection
	@MapKeyColumn(name = "feature_name")
	private Map<String, String> features = new LinkedHashMap<>();
	
	private Status status;
	
	private String image;
	
	@ElementCollection
	@CollectionTable(name = "PRODUCT_IMAGES")
	private List<String> images;
	
	@OneToMany(mappedBy = "product")
	private List<ProductHistory> history;
	
	public enum Status {
		Active, SoldOut, Deleted
	}
}
