package com.jdc.mkt.projection.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.mkt.model.projection.Inf.ProductProjectionInf;
import com.jdc.mkt.model.projection.record.ProductWithCategory;
import com.jdc.mkt.model.projection.repo.ProductProjectionRepo;
import com.jdc.mkt.model.repo.ProductDynamicRepo;

@SpringBootTest
@Sql(scripts = "/product.sql")
public class ProductTest {

	@Autowired
	ProductProjectionRepo repo;
	@Autowired
	ProductDynamicRepo dynamic;
	
	//@Test
	void test1() {
		var list1 = repo.findByCategoryName("Fruits");
		for(var p :list1) {
			System.out.println(p.showDisplay());
			System.out.println(p.showCategory());
		}
	}
	
	//@Test
	@DisplayName("02.projection with class based and jpql query")
	void test2() {
		var list = repo.findByProductNameWithDetailCount("Milk");
		for(var p :list) {
			System.out.println(p.name()+"\t"+p.detailPrice()+"\t"+p.saleDetailCount());
		}
	}
	
	
	//@Test
	@DisplayName("02.projection with class based and custom query method")
	void test3() {
		var list = repo.findByCategoryId(1);
		for(var p :list) {
			System.out.println(p.showDisplay());
		}
	}
	
	@Test
	@DisplayName("02.projection with dynamic ")
	void test4() {
		//var p = dynamic.findById(1,ProductProjectionInf.class);
		var p = dynamic.findById(1, ProductWithCategory.class);
		System.out.println(p.showDisplay());
		
	}
}
