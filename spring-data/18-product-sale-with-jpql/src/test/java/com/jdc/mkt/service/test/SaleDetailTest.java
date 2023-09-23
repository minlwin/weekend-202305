package com.jdc.mkt.service.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.model.dto.ProductByTownshipAndDate;
import com.jdc.mkt.model.service.SaleDetailService;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Sql(scripts = "classpath:/product.sql")
public class SaleDetailTest {
	@Autowired
	SaleDetailService service;
	
	//@ParameterizedTest
	@CsvSource("Chan Aye Thar Zan,2022-01-12")
	void searchByTownshipAndDate(String township,String date) {
		var list = service.getProductByTownshipAndDate(township, getDate(date));
		for(ProductByTownshipAndDate p :list) {
			System.out.println("Product Name :%s price :%d qty :%d "
					.formatted(p.name(),p.price(),p.qty()));
			
		}
	}
	
	@ParameterizedTest
	@CsvSource("Milk,2022-01-12,2022-04-05")
	void searchQtyByProductBetTwoDate(String product,String from_dt,String to_date) {
		var qty = service.getQtyByProductBetTwoDate(product,getDate(from_dt), getDate(to_date));
		System.out.println("%s : %d ".formatted(product,qty));
	}
	
	private LocalDate getDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
