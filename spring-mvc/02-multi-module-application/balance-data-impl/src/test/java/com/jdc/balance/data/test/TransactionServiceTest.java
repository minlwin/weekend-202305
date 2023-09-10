package com.jdc.balance.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		"thidar@gmail.com,,,,,3",
		"thidar@gmail.com,Credit,,,,2",
		"thidar@gmail.com,,ice,,,3",
		"thidar@gmail.com,,vice,2023-09-02,,1",
		"thidar@gmail.com,,vice,,2023-09-01,1",
	})
	void test_search(String username, LedgerType type, String ledger, LocalDate from, LocalDate to, int size) {
		var result = service.search(username, Optional.ofNullable(type), Optional.ofNullable(ledger), Optional.ofNullable(from), Optional.ofNullable(to));
		assertEquals(size, result.size());
	}
	
	@ParameterizedTest
	@CsvSource({
		"thidar@gmail.com,2023-09-01,2023-09-01,9000",
		"thidar@gmail.com,2023-09-02,2023-09-02,-16000",
		"thidar@gmail.com,2023-09-01,2023-09-07,1031429",
		"thidar@gmail.com,2023-09-06,2023-09-07,1031429",
	})
	void test_search_balance(String username, LocalDate from, LocalDate to, long balance) {
		var result = service.searchBalance(username, Optional.ofNullable(from), Optional.ofNullable(to));
		var data = result.get(result.size() - 1);
		assertEquals(balance, data.balance());
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
		
		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#creationTestEmptyError")
		void test_empty(TransactionForm form) {
			assertThrows(DataIntegrityViolationException.class, () -> service.create(form));
		}
		
		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#creationTestNoItemsError")
		void test_no_items(TransactionForm form) {
			assertThrows(IllegalArgumentException.class, () -> service.create(form));
		}
		
		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#creationTestNoUserError")
		void test_no_user(TransactionForm form) {
			assertThrows(IllegalArgumentException.class, () -> service.create(form));
		}
	}
	
	@Nested
	class UpdateTest {
		
		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#updateTestSuccess")
		void test_success(long id, TransactionForm form) {
			var result = service.update(id, form);
			
			assertEquals(id, result.transaction().id());
			assertEquals(form.getItems().stream()
					.mapToInt(a -> a.getQuantity() * a.getUnitPrice()).sum(), 
					result.transaction().amount());
		}
		
		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#updateTestEmptyError")
		void test_empty_error(long id, TransactionForm form) {
			assertThrows(DataIntegrityViolationException.class, () -> service.update(id, form));
		}

		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#updateTestNoItemError")
		void test_no_item_error(long id, TransactionForm form) {
			assertThrows(IllegalArgumentException.class, () -> service.update(id, form));
		}

		@ParameterizedTest
		@MethodSource("com.jdc.balance.data.test.utils.TransactionServiceTestInputs#updateTestNoUserError")
		void test_no_user_error(long id, TransactionForm form) {
			assertThrows(IllegalArgumentException.class, () -> service.update(id, form));
		}
	}	
	
	@Nested
	class FindByIdTest {
		
		@ParameterizedTest
		@CsvSource({
			"1,Service Charges,2023-09-01,2,9000",
			"2,Office Usage,2023-09-02,1,25000",
			"3,Comercial Fees,2023-09-03,1,130000",
			"4,Monthly Subscription Fees,2023-09-04,1,369147",
			"5,Monthly Subscription Fees,2023-09-05,1,667280",
			"6,Monthly Subscription Fees,2023-09-06,1,13404847",
			"7,Service Charges,2023-09-07,3,1047429"
		})
		void test_success(int id, String ledger, LocalDate issueAt, int items, int total) {
			var result = service.findById(id);
			assertTrue(result.isPresent());
			var data = result.get();
			
			assertEquals(id, data.transaction().id());
			assertEquals(ledger, data.transaction().ledgerName());
			assertEquals(issueAt, data.transaction().issueAt());
			assertEquals(items, data.items().size());
			assertEquals(total, data.total());
		}
		
		@ParameterizedTest
		@ValueSource(longs = {0, 8})
		void test_not_found(long id) {
			var result = service.findById(id);
			assertTrue(result.isEmpty());
		}
	}

}
