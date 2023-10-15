package com.jdc.onestop.weekend.products.model.form;

import java.util.List;
import java.util.Map;

import com.jdc.onestop.weekend.products.model.entity.Product.Status;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductEditForm {

	@NotBlank(message = "Please enter product name.")
	private String name;
	@NotBlank(message = "Please enter product description.")
	private String description;
	@Min(value = 1000, message = "Please enter price more thand 1000.")
	private int price;
	@NotNull(message = "Please select status of product.")
	private Status status;
	
	@NotEmpty(message = "Please enter features of product.")
	private Map<String, String> features;
	@NotEmpty(message = "Please select categories.")
	private List<Integer> categories;
}
