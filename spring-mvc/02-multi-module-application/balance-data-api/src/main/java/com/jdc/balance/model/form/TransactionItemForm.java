package com.jdc.balance.model.form;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class TransactionItemForm {

	@NotBlank(message = "Please enter item name.")
	private String item;
	@Min(value = 100, message = "Please enter unit price.")
	private int unitPrice;
	@Min(value = 1, message = "Please enter quantity.")
	private int quantity;
}
