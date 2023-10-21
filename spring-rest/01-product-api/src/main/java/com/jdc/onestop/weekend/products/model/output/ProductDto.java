package com.jdc.onestop.weekend.products.model.output;

import com.jdc.onestop.weekend.products.model.entity.Product;
import com.jdc.onestop.weekend.products.model.entity.Product_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private int id;
	private String name;
	private int price;
	private String description;
	private String image;
	
	public static void select(CriteriaQuery<ProductDto> query, Root<Product> root) {
		query.multiselect(
			root.get(Product_.id),
			root.get(Product_.name),
			root.get(Product_.price),
			root.get(Product_.description),
			root.get(Product_.image)
		);
	}
}
