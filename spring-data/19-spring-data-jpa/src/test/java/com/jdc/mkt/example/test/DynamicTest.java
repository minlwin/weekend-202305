package com.jdc.mkt.example.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.mkt.model.service.ProductService;

@SpringBootTest
public class DynamicTest {

	@Autowired
	ProductService service;
	
	@Test
	void testSearch() {
		var list1 = service.dynamicWithJpqlSearch(2, "E", null, 0);
		assertEquals(1, list1.size());
		var list2 = service.dynamicWithJpqlSearch(0, null, "Fruits", 0);
		assertEquals(5, list2.size());
		var list3 = service.dynamicWithJpqlSearch(0, null, "Fruits", 1500);
		assertEquals(3, list3.size());
		var list4 = service.dynamicWithJpqlSearch(0, "Eggs", "Diary", 0);
		assertEquals(1, list4.size());
		var list5 = service.dynamicWithJpqlSearch(13, "R", "Drinks", 1000);
		assertEquals(1, list5.size());
	}
}
