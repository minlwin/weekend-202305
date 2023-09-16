package com.jdc.balance.model.form;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionForm {

	private String username;
	@NotNull(message = "Please select ledger.")
	private Integer ledgerId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "Please enter issue date.")
	private LocalDate issueAt;
	
	@NotBlank(message = "Please enter remark.")
	private String remark;
	
	@NotEmpty(message = "Please enter transaction items.")
	private List<TransactionItemForm> items;

	private boolean deleted;
}
