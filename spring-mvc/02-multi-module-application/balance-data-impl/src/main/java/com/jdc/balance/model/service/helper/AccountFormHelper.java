package com.jdc.balance.model.service.helper;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.jdc.balance.model.form.AccountForm;

public class AccountFormHelper {

	public static Map<String, Object> convert(AccountForm form) {
		
		var map = new HashMap<String, Object>();
		map.put("name", form.getName());
		map.put("email", form.getEmail());
		map.put("password", form.getPassword());
		map.put("role", form.getRole().name());
		map.put("regist_ad", Date.valueOf(form.getRegistAt()));
		map.put("activated", form.isActivated());
		map.put("deleted", form.isDeleted());
		
		return map;
	}
}
