package com.jdc.aop.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
	
	@Autowired
	private BusinessBean bean;

	@Test
	void contextLoads() {
		var result = bean.divide(10, 2);
		System.out.println(result);
	}

}
