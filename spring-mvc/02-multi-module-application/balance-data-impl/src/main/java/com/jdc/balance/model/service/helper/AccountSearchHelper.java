package com.jdc.balance.model.service.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.util.StringUtils;

import com.jdc.balance.model.constants.Role;

public class AccountSearchHelper implements SearchHelper {

	private StringBuffer sb;
	private List<Object> params = new ArrayList<>();

	public AccountSearchHelper(String base, Optional<Role> role, Optional<String> name, Optional<Boolean> deleted) {
		sb = new StringBuffer(base);
		if (null != role && role.isPresent()) {
			sb.append(" and role = ?");
			params.add(role.get().name());
		}

		if (null != name && name.filter(StringUtils::hasLength).isPresent()) {
			sb.append(" and lower(name) like ?");
			params.add(name.get().toLowerCase().concat("%"));
		}

		if (null != deleted && deleted.isPresent()) {
			sb.append(" and deleted = ?");
			params.add(deleted.get());
		}
	}

	public SearchHelper page(int page, int max) {
		sb.append("skip %d limit %s".formatted((page - 1) * max, max));
		return this;
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
