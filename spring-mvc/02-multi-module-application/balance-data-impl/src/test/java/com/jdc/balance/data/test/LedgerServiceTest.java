package com.jdc.balance.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestClassOrder;
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
import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.service.LedgerService;

@TestClassOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = DatabaseConfiguration.class)
@Sql(scripts = {
		"/sql/init-database.sql",
		"/sql/insert-account.sql",
		"/sql/insert-ledger.sql",
		"/sql/insert-transaction.sql",
})
public class LedgerServiceTest {
	
	@Autowired
	private LedgerService service;
	
	@ParameterizedTest
	@CsvSource({
		"thidar@gmail.com,,,,3",
		"thidar@gmail.com,,,false,2",
		"thidar@gmail.com,Debit,,false,1",
	})
	void test_search(String username, LedgerType type, String name, Boolean deleted, int size) {
		service.search(username, Optional.ofNullable(type), Optional.ofNullable(name), Optional.ofNullable(deleted));
	}
	
	@ParameterizedTest
	@CsvSource({
		",Debit,,false,1",
	})
	void test_search_with_no_user(String username, LedgerType type, String name, Boolean deleted) {
		assertThrows(IllegalArgumentException.class, () -> service.search(username, Optional.ofNullable(type), Optional.ofNullable(name), Optional.ofNullable(deleted)));
	}
	
	@Order(1)
	@Nested
	class CreationTest {

		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/creation.txt", delimiter = '\t')
		void test_success(String username, LedgerType type, String name, boolean deleted, int id, long amount, long count) {
			
			var form = new LedgerForm();
			form.setUsername(username);
			form.setType(type);
			form.setName(name);
			form.setDeleted(deleted);
			
			var result = service.create(form);
			
			assertNotNull(result);
			assertEquals(id, result.id());
			assertEquals(type, result.type());
			assertEquals(amount, result.transactionAmount());
			assertEquals(count, result.transactionCount());
		
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/creation-empty.txt", delimiter = '\t')
		void test_empty(String username, LedgerType type, String name, boolean deleted) {
			var form = new LedgerForm();
			form.setUsername(username);
			form.setType(type);
			form.setName(name);
			form.setDeleted(deleted);
			
			assertThrows(DataIntegrityViolationException.class, () -> service.create(form));
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/creation-duplicate.txt", delimiter = '\t')
		void test_duplication(String username, LedgerType type, String name, boolean deleted) {
			var form = new LedgerForm();
			form.setUsername(username);
			form.setType(type);
			form.setName(name);
			form.setDeleted(deleted);
			
			assertThrows(DuplicateKeyException.class, () -> service.create(form));
		}
	}
	
	@Order(2)
	@Nested
	class FindByIdTest {
		
		
		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/find_by_id.txt", delimiter = '\t')
		void test_success(int id, String name, LedgerType type, long count, long amount) {
			
			var result = service.findById(id);
			
			assertTrue(result.isPresent());
			var data = result.get();
			
			assertEquals(id, data.id());
			assertEquals(name, data.name());
			assertEquals(type, data.type());
			assertEquals(count, data.transactionCount());
			assertEquals(amount, data.transactionAmount());
		}
		
		@ParameterizedTest
		@ValueSource(ints = {0, 7})
		void test_empty(int id) {
			var result = service.findById(id);
			assertTrue(result.isEmpty());
		}
		
	}
	
	@Order(3)
	@Nested
	class UpdateTest {
		
		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/update.txt", delimiter = '\t')
		void test_success(int id, String username, LedgerType type, String name, boolean deleted, long amount, long count) {
			var form = new LedgerForm();
			form.setUsername(username);
			form.setType(type);
			form.setName(name);
			form.setDeleted(deleted);
			
			var result = service.update(id, form);

			assertNotNull(result);
			assertEquals(id, result.id());
			assertEquals(type, result.type());
			assertEquals(amount, result.transactionAmount());
			assertEquals(count, result.transactionCount());
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/update_not_found.txt", delimiter = '\t')
		void test_not_found(int id, String username, LedgerType type, String name, boolean deleted) {
			var form = new LedgerForm();
			form.setUsername(username);
			form.setType(type);
			form.setName(name);
			form.setDeleted(deleted);
			
			assertThrows(NoSuchElementException.class, () -> service.update(id, form));
		}
		
	}

}
