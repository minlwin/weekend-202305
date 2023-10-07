package com.jdc.mkt.dynamicSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.mkt.model.repo.custom.ProductDynamicSearchRepositoryInt;

@SpringBootTest
public class ProductDyanmicSearchTest {
	
	@Autowired
	ProductDynamicSearchRepositoryInt repo;

//	@ParameterizedTest
//	@CsvSource({"1,,,0","0,e,,0","0,,Fruits,0","0,,,2000"})
//	void search(int id,String pname,String cname,int price) {
//		var list = repo.search(id, pname, cname, price);
//		System.out.println("List size : "+list.size());
//	}
	
	
}
