package com.jdc.onestop.weekend.products.model.form;

import com.jdc.onestop.weekend.products.model.entity.Product.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateForm {

	@NotNull(message = "Please select status to change.")
	private Status status;
	@NotBlank(message = "Please enter status change reason.")
	private String remark;
}
