package com.jdc.mkt.example.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.projection.Inf.CategoryProjectionInf;
import com.jdc.mkt.model.repo.ProductRepo;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {

	@Autowired
	ProductRepo repo;
	
	//@ParameterizedTest
	@CsvSource({"1,Milk","2,Eggs"})
	@Order(1)
	void findOneByName(int id,String name) {
		var probe = new Product();
		probe.setName(name);
		
		var example = Example.of(probe);
		var result = repo.findOne(example);
		assertEquals(id, result.get().getId());
	}
	
	@ParameterizedTest
	@CsvSource({"1,m","4,a"})
	@Order(2)
	void findAllByNameLike(int id,String name) {
		var probe = new Product();
		probe.setName(name);
		
		var matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.STARTING);
		
		var example = Example.of(probe,matcher);
		
		var result = repo.findBy(example,p -> p.as(CategoryProjectionInf.class).all());
		
		System.out.println(result.get(0).getId());
	}
	

	//@ParameterizedTest
	@CsvSource({"5,diary","4,Snacks"})
	@Order(3)
	void findCountByCategory(int count ,String name) {
		var probe = new Product();
		probe.setCategory(new Category(name));
		
		var example = Example.of(probe,ExampleMatcher.matching().withIgnoreCase());
		var result = repo.count(example);
		assertEquals(count,result);
	}
	
	
	
	//@ParameterizedTest
	@CsvSource({"1,m","2,a"})
	@Order(4)
	void findByByName(int size,String name) {
		var probe = new Product();
		probe.setName(name);
		
		var matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.STARTING);
		
		var example = Example.of(probe,matcher);
		
		
	}
	
	
	
	
}
