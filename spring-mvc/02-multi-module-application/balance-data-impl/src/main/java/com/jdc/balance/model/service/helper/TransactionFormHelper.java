package com.jdc.balance.model.service.helper;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.form.TransactionForm;
import com.jdc.balance.model.form.TransactionItemForm;

public class TransactionFormHelper {

	public static Map<String, Object> insertParams(TransactionForm form,
			Function<String, AccountDto> accountConverter) {

		var params = new HashMap<String, Object>();

		var account = accountConverter.apply(form.getUsername());
		params.put("account_id", account.id());
		params.put("ledger_id", form.getLedgerId());
		params.put("issue_at", form.getIssueAt() == null ? null : Date.valueOf(form.getIssueAt()));
		params.put("remark", form.getRemark());
		params.put("deleted", form.isDeleted());
		return params;
	}
	
	public static Map<String, Object> insertParams(long id, TransactionItemForm form) {

		var params = new HashMap<String, Object>();
		params.put("transaction_id", id);
		params.put("item", form.getItem());
		params.put("quantity", form.getQuantity());
		params.put("unit_price", form.getUnitPrice());

		return params;
	}
	
	
}
