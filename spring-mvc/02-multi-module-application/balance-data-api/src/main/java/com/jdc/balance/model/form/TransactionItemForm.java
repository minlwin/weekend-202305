package com.jdc.balance.model.form;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull(message = "Please enter unit price.")
	private int unitPrice;
	@NotNull(message = "Please enter quantity.")
	private int quantity;
}
