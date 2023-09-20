package com.jdc.balance.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.balance.model.config.DatabaseConfiguration;
import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.form.AccountForm;
import com.jdc.balance.model.service.AccountService;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = DatabaseConfiguration.class)
@Sql(scripts = {
		"/sql/init-database.sql",
		"/sql/insert-account.sql",
})
public class AccountServiceTest {

	@Autowired
	private AccountService service;
	
	@Order(1)
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
	
	
	@Order(2)
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

	@Order(3)
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
	
	@Order(4)
	@ParameterizedTest
	@CsvFileSource(resources = "/csv/account/find-one.txt", delimiter = '\t')
	void test_find_by_email_found(String email, int id, String name, Role role, LocalDate registAt, boolean activated, boolean deleted) {
		var result = service.findByEmail(email);
		assertTrue(result.isPresent());
		
		var data = result.get();
		assertEquals(id, data.id());
		assertEquals(email, data.email());
		assertEquals(name, data.name());
		assertEquals(role, data.role());
		assertEquals(registAt, data.registAt());
		assertEquals(activated, data.activated());
		assertEquals(deleted, data.deleted());
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(strings = {
		"",
		"thaung@gmail.coms",
		"thaung@gmail.co",
		"Thaung@gmail.com"
	})
	void test_find_by_email_not_found(String email) {
		
		var result = service.findByEmail(email);
		assertTrue(result.isEmpty());
	}
	
	@Order(6)
	@ParameterizedTest
	@CsvFileSource(resources = "/csv/account/update.txt", delimiter = '\t')
	void test_update(int id, String name, String email, Role role, String password, LocalDate registAt, boolean activated, boolean deleted) {
		
		var form = new AccountForm();
		form.setName(name);
		form.setEmail(email);
		form.setRole(role);
		form.setPassword(password);
		form.setRegistAt(registAt);
		form.setActivated(activated);
		form.setDeleted(deleted);
		
		var result = service.update(id, form);

		// check result
		assertNotNull(result);
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(email, result.email());
		assertEquals(role, result.role());
		assertEquals(registAt, result.registAt());
	}
	
	@Order(7)
	@ParameterizedTest
	@CsvFileSource(resources = "/csv/account/update-duplicate.txt", delimiter = '\t')
	void test_update_duplicate(int id, String name, String email, Role role, String password, LocalDate registAt, boolean activated, boolean deleted) {
		var form = new AccountForm();
		form.setName(name);
		form.setEmail(email);
		form.setRole(role);
		form.setPassword(password);
		form.setRegistAt(registAt);
		form.setActivated(activated);
		form.setDeleted(deleted);
		
		assertThrows(DuplicateKeyException.class, () -> service.update(id, form));
	}
	
	@Order(8)
	@ParameterizedTest
	@CsvFileSource(resources = "/csv/account/update-empty.txt", delimiter = '\t')
	void test_update_empty(int id, String name, String email, Role role, String password, LocalDate registAt, boolean activated, boolean deleted) {
		var form = new AccountForm();
		form.setName(name);
		form.setEmail(email);
		form.setRole(role);
		form.setPassword(password);
		form.setRegistAt(registAt);
		form.setActivated(activated);
		form.setDeleted(deleted);
		
		assertThrows(DataIntegrityViolationException.class, () -> service.update(id, form));
	}

	@Order(8)
	@ParameterizedTest
	@CsvSource({
		"7,TestName,test@gmail.com,Admin,test,2023-09-09,true,false"
	})
	void test_update_no_data(int id, String name, String email, Role role, String password, LocalDate registAt, boolean activated, boolean deleted) {
		var form = new AccountForm();
		form.setName(name);
		form.setEmail(email);
		form.setRole(role);
		form.setPassword(password);
		form.setRegistAt(registAt);
		form.setActivated(activated);
		form.setDeleted(deleted);
		
		assertThrows(NoSuchElementException.class, () -> service.update(id, form));
	}
	
	@Order(8)
	@ParameterizedTest
	@CsvSource({
		",,,6",
		"Admin,,,2",
		",th,,3",
		",,true,0",
		"Admin,thau,false,1"
	})
	void test_search(Role role, String name, Boolean deleted, int size) {
		
		var result = service.search(Optional.ofNullable(role), Optional.ofNullable(name), Optional.ofNullable(deleted));
		
		assertEquals(size, result.size());
	}	
}
