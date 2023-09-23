package com.jdc.mkt.repo.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.entity.SaleDetailsPk;
import com.jdc.mkt.model.repo.SaleDetailsRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Sql(scripts = "classpath:/product.sql")
public class SaleDetailsTest {

	@Autowired
	SaleDetailsRepo repo;
	
	@Test
	void test() {		
		repo.deleteAllById(List.of(new SaleDetailsPk(1, 1),new SaleDetailsPk(1,3)));
		System.out.println("Size :"+repo.findAll().size());
	}
}
