package com.jdc.onestop.weekend.products.model.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryEditForm {

	private int id;
	@NotBlank(message = "Please enter category name.")
	private String name;
}
