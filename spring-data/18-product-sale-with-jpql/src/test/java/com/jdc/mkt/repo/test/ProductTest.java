package com.jdc.mkt.repo.test;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.repo.ProductRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Sql(scripts = "classpath:/product.sql")
public class ProductTest {

	@Autowired
	ProductRepo repo;
	
	@ParameterizedTest
	@CsvSource("Bottle,10000,9000,Accessories")
	void insertProduct(String name,int dt_price,int ws_price,String category) {
		var c = new Category(category);
		var np = new Product(name, dt_price, ws_price, c);
		var product =  repo.save(np);
		System.out.println(product.getId() +"\t"+product.getName());
		
		var count = repo.count();
		System.out.println("Count  :"+count);
		
		var products = repo.findAllById(List.of(1,3,5));
		System.out.println("Size :"+products.size());
		
		
		repo.deleteById(1);
		System.out.println(repo.findAll().size());
	
	}
}
