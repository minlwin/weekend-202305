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
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.projection.Inf.ProductProjectionInf;
import com.jdc.mkt.model.repo.ProductRepo;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProductMatcherTest {

	@Autowired
	ProductRepo repo;
	
	@Order(1)
	//@ParameterizedTest
	@CsvSource("6,candy")
	void usingWithNullHandler(int id,String name) {
		var product = new Product();
		product.setName(name);
		var example = Example.of(product, 
				ExampleMatcher
				.matching()
				//.withIgnoreNullValues()
				.withNullHandler(NullHandler.IGNORE)
				.withIgnoreCase(true)
				);
		
		var result = repo.findOne(example);
		assertEquals(id, result.get().getId());
	}
	@Order(2)
	@ParameterizedTest
	@CsvSource("6,candy")
	void usingWithPropertiesPathMatching(int id,String name) {
		var product = new Product();
		product.setName(name);
		product.setId(6);
		product.setDetailPrice(700);
		product.setCategory(new Category("snacks"));
		
		var example = Example.of(product, 
				ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withIgnorePaths("id","detailPrice","category.id"));
		
		
		var result = repo.findBy(example,query ->query.as(ProductProjectionInf.class).all());
		result.forEach(p ->System.out.println( p.showDisplay()));
	}
	
	@Order(3)
	//@ParameterizedTest
	@CsvSource("6,c")
	void usingWithMacher(int id,String name) {
		var product = new Product();
		product.setName(name);
		product.setId(6);
		product.setDetailPrice(700);
		product.setCategory(new Category("Snacks"));
		
		var example = Example.of(product,
				ExampleMatcher.matching()
				.withMatcher("category.name", m -> m.exact())
				.withMatcher("name", m -> m.ignoreCase().startsWith())
				.withIgnorePaths("id","detailPrice"));
				
		
		var result = repo.findOne(example);
		assertEquals(id, result.get().getId());
	}
	
	@Order(4)
	//@ParameterizedTest
	@CsvSource("18,a")
	void usingWithFetchableFluentQuery(int id,String name) {
		var product = new Product();
		product.setName(name);
		
		var example = Example.of(product,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.STARTING));
				
		var result = repo.findBy(example,
				query -> query
					//.as(Product.class)
					.project("name")
					.sortBy(Sort.by(Direction.ASC,"detailPrice"))
					.first()
					);
		System.out.println(result.get().getName()+
				"\t"+result.get().getDetailPrice()
				+"\t"+result.get().getWholeSalePrice());
	}
	
	
}
