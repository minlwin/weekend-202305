package com.jdc.balance.model.service.impl;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.form.AccountForm;
import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.model.service.helper.AccountFormHelper;

@Service
public class AccountServiceImpl implements AccountService{
	
	private SimpleJdbcInsert insert;
	private JdbcTemplate template;
	
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
				"regist_ad", 
				"activated", 
				"deleted"));
	}

	@Override
	@Transactional
	public AccountDto create(AccountForm form) {
		var id = insert.executeAndReturnKey(AccountFormHelper.convert(form)).intValue();
		return new AccountDto(id, form.getName(), form.getEmail(), form.getRole(), form.getRegistAt(), form.isActivated(), form.isDeleted());
	}

	@Override
	@Transactional
	public AccountDto update(int id, AccountForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AccountDto> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<AccountDto> search(Optional<Role> role, Optional<String> name, Optional<Boolean> deleted) {
		// TODO Auto-generated method stub
		return null;
	}

}
