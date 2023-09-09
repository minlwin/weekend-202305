package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.repo.ProductRepoImpl;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Sql(scripts = "classpath:/product.sql")
public class JpqlTest {
	
	@Autowired
	private ProductRepoImpl repo;

	@Test
	void test() {
		repo.findById(1);
	}

}
