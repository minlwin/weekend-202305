package com.jdc.balance.data.test.utils;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.balance.model.config.DatabaseConfiguration;

@SpringJUnitConfig(classes = DatabaseConfiguration.class)
@Sql(scripts = {
		"/sql/init-database.sql",
})
public class ResetDatabase {

	@Test
	void test() {
		System.out.println("Database has been reset!");
	}
}
