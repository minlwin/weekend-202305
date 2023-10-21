package com.jdc.onestop.weekend.products.model.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.onestop.weekend.products.model.entity.Product.Status;
import com.jdc.onestop.weekend.products.model.entity.ProductHistory;

import lombok.Data;

@Data
public class ProductHistoryDto {

	private int id;
	private int version;
	private String name;
	private int price;
	private String description;
	private List<CategoryDto> categories;
	private Map<String, String> features;
	
	private Status status;
	
	private String remark;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime createAt;
	
	public ProductHistoryDto(ProductHistory entity) {
		this.id = entity.getId().getProductId();
		this.version = entity.getId().getVersion();
		this.name = entity.getName();
		this.price = entity.getPrice();
		this.description = entity.getDescription();
		this.categories = entity.getCategories().stream()
				.map(CategoryDto::new)
				.toList();
		this.features = entity.getFeatures();
		this.status = entity.getStatus();
		this.remark = entity.getRemark();
	}

}
