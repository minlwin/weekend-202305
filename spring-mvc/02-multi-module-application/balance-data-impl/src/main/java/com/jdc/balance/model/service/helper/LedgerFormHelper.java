package com.jdc.balance.model.service.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.form.LedgerForm;

public class LedgerFormHelper {

	public static Map<String, Object> insertParams(LedgerForm form,
			Function<String, AccountDto> accountConverter) {
		var account = accountConverter.apply(form.getUsername());
		var params = new HashMap<String, Object>();
		params.put("account_id", account.id());
		params.put("name", form.getName());
		params.put("type", form.getType() == null ? null : form.getType().name());
		params.put("deleted", form.isDeleted());
		return params;
	}
}
