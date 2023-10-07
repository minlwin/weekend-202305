package com.jdc.mkt.criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.mkt.services.ProductCriteriaService;

@SpringBootTest
public class ProductCriteraTest {

	@Autowired
	ProductCriteriaService service;

	// @ParameterizedTest
	@CsvSource({ "Fruits,5", "Diary,5", "Snacks,4" })
	void searchByCatName(String name, int size) {
		var list = service.getProductByCatNameWithSpecApi(name);
		list.forEach(p -> System.out
				.println("Name :" + p.getName() + "\tPrice :" + p.getDetailPrice() + "\tCategory :" + p.getCategory()));
		assertEquals(size, list.size());
	}

	// @ParameterizedTest
	@CsvSource({ "Fruits,5", "Diary,5", "Snacks,4" })
	void searchCountByCatName(String name, int size) {
		var count = service.getProductCountByCatName(name);
		assertEquals(size, count);
	}

	@ParameterizedTest
	@CsvSource({ "e,1000", "a,5", "s,500" })
	void searchByNameLikeAndPrice(String name, int price) {
		var list = service.getCategoryByProductPrice(name, price);
		System.out.println(list.size());
	}

	// @ParameterizedTest
	@CsvSource("Eggs,1")
	void deleteByProductName(String name, long c) {
		System.out.println("Size :" + service.getAllProductCount());
		var count = service.deleteByProductName(name);
		System.out.println("Size :" + service.getAllProductCount());
	}
}
