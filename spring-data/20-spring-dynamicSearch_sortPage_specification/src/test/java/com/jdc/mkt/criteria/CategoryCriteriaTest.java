package com.jdc.mkt.criteria;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.mkt.services.CategoryCriteriService;

@SpringBootTest
public class CategoryCriteriaTest {
	@Autowired
	CategoryCriteriService service;

	@ParameterizedTest
	@CsvSource("Fruits")
	void deleteByCatName(String name) {
		var count = service.deleteCategoryByName(name);
		System.out.println(count);
	}
	
	
}
