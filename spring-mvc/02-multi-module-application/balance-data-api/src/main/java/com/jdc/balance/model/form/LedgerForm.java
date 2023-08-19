package com.jdc.balance.model.form;

import com.jdc.balance.model.constants.LedgerType;

import lombok.Data;

@Data
public class LedgerForm {

	private int id;
	private String name;
	private LedgerType type;
	private boolean deleted;
}
