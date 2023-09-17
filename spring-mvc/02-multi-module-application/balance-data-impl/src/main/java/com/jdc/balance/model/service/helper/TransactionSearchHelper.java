package com.jdc.balance.model.service.helper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.util.StringUtils;

import com.jdc.balance.model.constants.LedgerType;

public class TransactionSearchHelper implements SearchHelper{

	private StringBuffer sb;
	private List<Object> params = new ArrayList<>();

	public TransactionSearchHelper(String select, String groupBy,
			String username, 
			Optional<LedgerType> type, 
			Optional<String> ledger,
			Optional<LocalDate> from, 
			Optional<LocalDate> to) {
		sb = new StringBuffer(select);
		sb.append(" where a.email = ?");
		params.add(username);

		if(null != type && type.isPresent()) {
			sb.append(" and l.type = ?");
			params.add(type.get().name());
		}
		
		if(null != ledger && ledger.filter(StringUtils::hasLength).isPresent()) {
			sb.append(" and lower(l.name) like ?");
			params.add("%%%s%%".formatted(ledger.get().toLowerCase()));
		}
		
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
