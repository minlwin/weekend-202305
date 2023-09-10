package com.jdc.balance.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.balance.model.config.DatabaseConfiguration;
import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.form.TransactionForm;
import com.jdc.balance.model.service.TransactionService;

@SpringJUnitConfig(classes = DatabaseConfiguration.class)
@Sql(scripts = {
		"/sql/init-database.sql",
		"/sql/insert-account.sql",
		"/sql/insert-ledger.sql",
		"/sql/insert-transaction.sql",
})
public class TransactionServiceTest {
	
	@Autowired
	private TransactionService service;
	
	@ParameterizedTest
	@CsvSource({
		
	})
	void test_search(String username, Optional<LedgerType> type, Optional<String> ledger, Optional<LocalDate> from, Optional<LocalDate> to, int size) {
		var result = service.search(username, type, ledger, from, to);
		assertEquals(size, result.size());
	}
	
	@ParameterizedTest
	@CsvSource({
		
	})
	void test_search_balance(String username, Optional<LocalDate> from, Optional<LocalDate> to, int size) {
		var result = service.searchBalance(username, from, to);
		assertEquals(size, result.size());
	}
	
	@Nested
	class CreationTest {
		
		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#creationTestSuccess")
		void test_success(TransactionForm form, long id) {
			
			var result = service.create(form);
			
			assertEquals(id, result.transaction().id());
			assertEquals(form.getItems().stream()
					.mapToInt(a -> a.getQuantity() * a.getUnitPrice()).sum(), 
					result.transaction().amount());
		}
		
		void test_empty() {
			
		}
		
	}
	
	@Nested
	class FindByIdTest {
		
		@Disabled
		@ParameterizedTest
		@CsvSource({
			""
		})
		void test_success() {
			
		}
		
		@Disabled
		@ParameterizedTest
		@ValueSource(longs = {0, 8})
		void test_not_found(long id) {
			var result = service.findById(id);
			assertTrue(result.isEmpty());
		}
	}
	
	@Nested
	class UpdateTest {
		
	}

}
