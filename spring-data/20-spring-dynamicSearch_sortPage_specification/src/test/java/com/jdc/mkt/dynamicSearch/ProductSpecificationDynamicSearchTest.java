package com.jdc.mkt.dynamicSearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.mkt.services.ProductCriteriaService;

@SpringBootTest
public class ProductSpecificationDynamicSearchTest {

	@Autowired
	ProductCriteriaService service;
	
	@ParameterizedTest
	@CsvSource({"e,,","e,Diary,",",,1000"})
	void search(String pname,String cname,Integer price) {
		var list = service.search(pname, cname, price);
		System.out.println("List Size ::::::"+list.size());
	}
}
