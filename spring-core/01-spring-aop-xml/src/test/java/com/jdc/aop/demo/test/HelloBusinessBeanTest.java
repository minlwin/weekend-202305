package com.jdc.aop.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.aop.demo.HelloBusiness;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class HelloBusinessBeanTest {
	
	@Autowired
	HelloBusiness bean;
	
	@Test
	void doBusinessTest() {
		bean.doBusiness();
	}

}
