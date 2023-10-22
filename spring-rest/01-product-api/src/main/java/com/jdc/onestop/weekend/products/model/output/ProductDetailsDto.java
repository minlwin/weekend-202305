package com.jdc.onestop.weekend.products.model.output;

import java.util.List;
import java.util.Map;

import com.jdc.onestop.weekend.products.model.entity.Product;
import com.jdc.onestop.weekend.products.model.entity.Product.Status;

import lombok.Data;

@Data
public class ProductDetailsDto {
	private int id;
	private String name;
	private int price;
	private String description;
	private List<CategoryDto> categories;
	private Map<String, String> features;
	
	private Status status;
	
	private String image;
	
	private List<String> images;
	private List<ProductHistoryDto> history;
	
	public ProductDetailsDto(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
		this.description = entity.getDescription();
		this.categories = entity.getCategories()
				.stream().map(CategoryDto::new).toList();
		this.features = entity.getFeatures();
		this.status = entity.getStatus();
		this.image = entity.getImage();
		this.images = entity.getImages();
		this.history = entity.getHistory().stream()
				.map(ProductHistoryDto::new).toList();
	}

}
