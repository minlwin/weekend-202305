package com.jdc.balance.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.balance.model.config.DatabaseConfiguration;
import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.form.AccountForm;
import com.jdc.balance.model.service.AccountService;

@SpringJUnitConfig(classes = DatabaseConfiguration.class)
@Sql(scripts = {
		"/sql/init-database.sql",
		"/sql/insert-account.sql",
})
public class AccountServiceTest {

	@Autowired
	private AccountService service;
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/csv/account/creation.txt", delimiter = '\t')
	void test_create(String name, String email, Role role, String password, LocalDate registAt, int id) {
		
		// prepare inputs
		var form = new AccountForm();
		form.setActivated(true);
		form.setName(name);
		form.setEmail(email);
		form.setRole(role);
		form.setPassword(password);
		form.setRegistAt(registAt);
		
		// execute target method
		var result = service.create(form);
		
		// check result
		assertNotNull(result);
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(email, result.email());
		assertEquals(role, result.role());
		assertEquals(registAt, result.registAt());
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/csv/account/creation-empty.txt", delimiter = '\t')
	void test_create_empty_error(String name, String email, Role role, String password, LocalDate registAt) {
		// prepare inputs
		var form = new AccountForm();
		form.setActivated(true);
		form.setName(name);
		form.setEmail(email);
		form.setRole(role);
		form.setPassword(password);
		form.setRegistAt(registAt);
		
		assertThrows(DataIntegrityViolationException.class, () -> service.create(form));

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/csv/account/creation-duplicate.txt", delimiter = '\t')
	void test_create_duplication_error(String name, String email, Role role, String password, LocalDate registAt) {
		// prepare inputs
		var form = new AccountForm();
		form.setActivated(true);
		form.setName(name);
		form.setEmail(email);
		form.setRole(role);
		form.setPassword(password);
		form.setRegistAt(registAt);
		
		assertThrows(DuplicateKeyException.class, () -> service.create(form));
	}
	
	
}
