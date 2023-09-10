package com.jdc.balance.model.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.form.AccountForm;
import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.model.service.helper.AccountFormHelper;

import lombok.val;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{
	
	private SimpleJdbcInsert insert;
	private JdbcTemplate template;
	
	private final RowMapper<AccountDto> rowMapper = (rs, rowNum) -> new AccountDto(
			rs.getInt("id"), 
			rs.getString("name"), 
			rs.getString("email"), 
			rs.getString("password"),
			Role.valueOf(rs.getString("role")), 
			rs.getDate("regist_at").toLocalDate(), 
			rs.getBoolean("activated"), 
			rs.getBoolean("deleted"));
	
	public AccountServiceImpl(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("account");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of(
				"name", 
				"email", 
				"password", 
				"role", 
				"regist_at", 
				"activated", 
				"deleted"));
	}

	@Override
	@Transactional
	public AccountDto create(AccountForm form) {
		var id = insert.executeAndReturnKey(AccountFormHelper.insertParams(form)).intValue();
		return findById(id);
	}

	@Override
	@Transactional
	public AccountDto update(int id, AccountForm form) {
		var sql = """
			update account set name = ?, email = ?, role = ?, regist_at = ?, 
			activated = ?, deleted = ? where id = ?""";
		
		template.update(sql, stmt -> {
			stmt.setString(1, form.getName());
			stmt.setString(2, form.getEmail());
			stmt.setString(3, null != form.getRole() ? form.getRole().name() : null);
			stmt.setDate(4, form.getRegistAt() == null ? null : Date.valueOf(form.getRegistAt()));
			stmt.setBoolean(5, form.isActivated());
			stmt.setBoolean(6, form.isDeleted());
			stmt.setInt(7, id);
		});
		
		return findById(id);
	}

	@Override
	public Optional<AccountDto> findByEmail(String email) {
		val sql = "select * from account where email = ?";
		return template.queryForStream(sql, rowMapper, email).findAny();
	}

	@Override
	public List<AccountDto> search(Optional<Role> role, Optional<String> name, Optional<Boolean> deleted) {
		
		var sql = new StringBuffer("select * from account where 1 = 1");
		var params = new ArrayList<Object>();
		
		if(null != role && role.isPresent()) {
			sql.append(" and role = ?");
			params.add(role.get().name());
		}
		
		if(null != name && name.filter(StringUtils::hasLength).isPresent()) {
			sql.append(" and lower(name) like ?");
			params.add(name.get().toLowerCase().concat("%"));
		}
		
		if(null != deleted && deleted.isPresent()) {
			sql.append(" and deleted = ?");
			params.add(deleted.get());
		}
		
		return template.query(sql.toString(), rowMapper, params.toArray());
	}

	private AccountDto findById(int id) {
		val sql = "select * from account where id = ?";
		return template.queryForStream(sql, rowMapper, id).findAny().get();
	}

	@Override
	public long getCount() {
		return template.queryForObject("select count(id) from account", Long.class);
	}

}
