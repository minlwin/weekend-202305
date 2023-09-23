package com.jdc.mkt.projection.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.mkt.model.projection.repo.CustomerProjectionRepo;

@SpringBootTest
@Sql(scripts = "/product.sql")
public class CustomerTest {

	@Autowired
	CustomerProjectionRepo repo;
	
	@Test
	void test1() {
		var list1 = repo.getCustomerByNameLike("a".concat("%"));
		for(var c :list1) {
			System.out.println(c.showDisplay());
		}
	}
}
