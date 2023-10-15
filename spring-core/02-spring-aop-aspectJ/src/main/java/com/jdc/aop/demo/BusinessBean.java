package com.jdc.aop.demo;

import org.springframework.stereotype.Service;

@Service
public class BusinessBean {

	public int divide(int digit1, int digit2) {
		System.out.println("Execution in Business Logic Method");
		return digit1 / digit2;
	}
}
