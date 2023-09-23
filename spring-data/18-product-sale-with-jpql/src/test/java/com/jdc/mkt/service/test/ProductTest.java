package com.jdc.mkt.service.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.model.service.ProductService;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Sql(scripts = "classpath:/product.sql")
public class ProductTest {

	@Autowired
	ProductService service;

	@ParameterizedTest
	@ValueSource(ints = 1)
	void testProduct(int id) {
		var product = service.findById(id);
		System.out.println(product.getDetailPrice());
	}
}
