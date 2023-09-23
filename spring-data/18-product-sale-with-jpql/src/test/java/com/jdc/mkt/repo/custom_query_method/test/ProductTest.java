package com.jdc.mkt.repo.custom_query_method.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.model.repo.ProductRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Sql(scripts = "/product.sql")
public class ProductTest {

	@Autowired
	ProductRepo repo;
	
	@ParameterizedTest
	@CsvSource({"2,Eggs","3,Butter"})
	void findByName(int id,String name) {
		var p = repo.findByName(name);
		assertEquals(id, p.getId());
	}
}
