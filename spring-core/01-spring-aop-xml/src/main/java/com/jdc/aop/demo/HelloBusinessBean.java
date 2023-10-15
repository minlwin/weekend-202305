package com.jdc.aop.demo;

public class HelloBusinessBean implements HelloBusiness {

	@Override
	public void doBusiness() {
		System.out.println("This is Business Logics");
	}
}
