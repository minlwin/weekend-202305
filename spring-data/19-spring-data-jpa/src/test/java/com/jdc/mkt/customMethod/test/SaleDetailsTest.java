package com.jdc.mkt.customMethod.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.mkt.model.repo.SaleDetailsRepo;

@SpringBootTest
@Sql(scripts = "/product.sql")
public class SaleDetailsTest {

	@Autowired
	SaleDetailsRepo repo;
	
	@Test
	@DisplayName("01.custom query method")
	void test() {
		repo.deleteProductByProductId(1);
		assertEquals(23, repo.findAll().size());
		
		var list1 = 
				repo.findByProductNameIgnoreCaseAndSalesId("eggs", 4);
		assertEquals(1, list1.size());
	}
}
