package com.jdc.mkt.customMethod.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.jdc.mkt.model.repo.ProductRepo;

@SpringBootTest
@Sql(scripts = "/product.sql")
public class ProductTest {

	@Autowired
	ProductRepo repo;
	
	//@Test
	@DisplayName("01.custom method")
	void test() {
		var prod = repo.searchProductByName("Eggs");
		assertEquals(2, prod.getId());
		
		var count = repo.countProductByCategoryName("Fruits");
		assertEquals(5,count);
		
		var exists = repo.existsProductByName("Eggs");
		assertTrue(exists);
		
		var list1 = repo.findTop3ByCategoryName("Fruits");
		assertEquals(3, list1.size());
		list1.forEach(p -> System.out.println(p.getName()));
		
		var list2 = repo.findDistinctByNameLikeIgnoreCase("g".concat("%"));
		System.out.println("list2 size :"+list2.size());
		
//		repo.deleteProductByCategoryId(1);
//		assertEquals(26, repo.findAll().size());
		
	}
	@Test
	@DisplayName("02.Named query ")
	void test2() {
		//var list1 = repo.selectByCategoryNameAndPrice("Diary", 3000);
		var list1 = repo.selectProductByBetTwoPrice(4000, 3000);
		
		for(var p :list1) {
			System.out.println("Name :%s\tPrice :%d".formatted(p.getName(),p.getDetailPrice()));
		}
	}
	
	//@Test
	@DisplayName("03.Named native query ")
	void test3() {
		var list1 = repo.selectProductByNamedLiked("g".concat("%"));
		
		for(var p :list1) {
			System.out.println("Name :%s\tPrice :%d".formatted(p.getName(),p.getDetailPrice()));
		}
	}
	
	//@Test
	@DisplayName("03.update with Named native query ")
	void test4() {
		repo.updateProductNameAndDtPrice("Cheese cake", 3500, 4);
		var p = repo.findById(4);
		System.out.println("Name :%s\tPrice :%d".formatted(p.get().getName(),p.get().getDetailPrice()));
	}
	
}







