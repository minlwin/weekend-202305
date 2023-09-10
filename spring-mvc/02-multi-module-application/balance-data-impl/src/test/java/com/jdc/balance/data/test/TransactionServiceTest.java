package com.jdc.balance.data.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.balance.model.config.DatabaseConfiguration;
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

}
