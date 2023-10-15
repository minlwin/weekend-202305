package com.jdc.onestop.weekend.products.model.output;

import com.jdc.onestop.weekend.products.model.entity.Category;

import lombok.Data;

@Data
public class CategoryDto {

	private int id;
	private String name;
	
	public CategoryDto(Category c) {
		this.id = c.getId();
		this.name = c.getName();
	}
}
