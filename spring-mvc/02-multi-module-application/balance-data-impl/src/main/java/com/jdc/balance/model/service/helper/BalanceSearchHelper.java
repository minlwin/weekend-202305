package com.jdc.balance.model.service.helper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BalanceSearchHelper implements SearchHelper{

	private StringBuffer sb;
	private List<Object> params = new ArrayList<>();
	
	public BalanceSearchHelper(String select, 
			String groupBy,
			String username,
			Optional<LocalDate> from, 
			Optional<LocalDate> to) {

		sb = new StringBuffer(select);
		sb.append(" where a.email = ?");
		params.add(username);

		if(null != from && from.isPresent()) {
			sb.append(" and t.issue_at >= ?");
			params.add(Date.valueOf(from.get()));
		}
		
		if(null != to && to.isPresent()) {
			sb.append(" and t.issue_at <= ?");
			params.add(Date.valueOf(to.get()));
		}

		sb.append(" ").append(groupBy);
		sb.append(" order by t.id");
		
	}

	@Override
	public String sql() {
		return sb.toString();
	}

	@Override
	public Object[] params() {
		return params.toArray();
	}


}
