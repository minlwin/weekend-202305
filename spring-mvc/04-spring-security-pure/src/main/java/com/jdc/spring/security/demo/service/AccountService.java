package com.jdc.spring.security.demo.service;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.security.demo.dto.Account;

@Service
public class AccountService {
	
	private SimpleJdbcInsert insert;
	private JdbcTemplate template;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AccountService(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("account");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of("name", "email", "password", "role"));
	}
	
	public Optional<Account> findByEmail(String email) {
		try {
			var result = template.queryForObject("select * from account where email = ?", 
					new DataClassRowMapper<Account>(Account.class), email);
			return Optional.of(result);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	@Transactional
	public int create(Account account) {
		return insert.executeAndReturnKey(account.insertParam(passwordEncoder::encode)).intValue();
	}
	
	public long count() {
		return template.queryForObject("select count(id) from account", Long.class);
	}
}
