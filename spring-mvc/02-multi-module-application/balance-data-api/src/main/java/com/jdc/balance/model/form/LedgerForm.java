package com.jdc.balance.model.form;

import com.jdc.balance.model.constants.LedgerType;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LedgerForm {

	private String username;
	
	@NotBlank(message = "Please enter ledger name.")
	private String name;
	@NotBlank(message = "Please enter ledger type.")
	private LedgerType type;
	private boolean deleted;
}
