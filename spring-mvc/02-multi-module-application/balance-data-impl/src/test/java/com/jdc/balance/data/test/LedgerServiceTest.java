package com.jdc.balance.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
})
public class LedgerServiceTest {
	
	@Autowired
	private LedgerService service;
	
	@Order(1)
	@Nested
	class CreationTest {

		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/creation.txt", delimiter = '\t')
		void test_create(String username, LedgerType type, String name, boolean deleted, int id, long amount, long count) {
			
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
		
		@Disabled
		@ParameterizedTest
		@CsvFileSource(resources = "/csv/ledger/creation-empty.txt", delimiter = '\t')
		void test_create_empty(String username, LedgerType type, String name, boolean deleted) {
			var form = new LedgerForm();
			form.setUsername(username);
			form.setType(type);
			form.setName(name);
			form.setDeleted(deleted);
			
			assertThrows(DataIntegrityViolationException.class, () -> service.create(form));
		}
	}
	
	@Disabled
	@Order(2)
	@Nested
	class FindByIdTest {
		
	}
	
	@Disabled
	@Order(3)
	@Nested
	class UpdateTest {
		
	}
	
	@Disabled
	@Order(4)
	@Nested
	class SearchTest {
		
	}
	

}
