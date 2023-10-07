package com.jdc.mkt.dynamicSearch;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.util.StringUtils;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.repo.custom.ProductRepositoryInt;

@SpringBootTest
public class ProductExampleDynamicSearchTest {

	@Autowired
	ProductRepositoryInt repo;
	
	@Test
	void findByNamelike() {
		var list =  repo.findByProductNameLikeSearch(null);
		System.out.println(list);
	}

//	@ParameterizedTest
	@CsvSource({"1,,,0","0,e,,0","0,,Fruits,0","0,,,2000"})
	void search(int id,String pname,String cname,int price) {
//		var list = repo.search(id, pname, cname, price);
//		System.out.println("List size : "+list.size());
		var list = searchProduct(id, pname, cname, price);
		System.out.println("list size :"+list.size());
	}
	
	List<Product> searchProduct(int id,String pname,String cname,int price) {
		var product = new Product();
		var category = new Category();
		product.setCategory(category);
		
		var excludes =new ArrayList<String>(List.of("id","category.id","category.isDeleted"));
		
		var matcher = ExampleMatcher.matching();
		
		if(id > 0) {
			excludes.remove("id");
			product.setId(id);
		}
		
		if(StringUtils.hasLength(cname)) {
			matcher = matcher.withMatcher(cname, c -> c.exact());
			category.setName(cname);
		}
		
		if(StringUtils.hasLength(pname)) {
			matcher = matcher.withIgnoreCase().withStringMatcher(StringMatcher.STARTING);
			product.setName(pname);
		}
		
		if(price > 0) {
			product.setDetailPrice(price);
		}
		
		matcher.withIgnorePaths(excludes.toArray(s -> new String[s]));
		
		var example = Example.of(product, matcher);
		return repo.findAll(example);
		
		
		
	}
}
