package com.jdc.aop.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAspects {

	@Pointcut("within(com.jdc.aop.demo.*)")
	public void demoClasses() {}
	
	@Before(
			value = "demoClasses() and args(digit1,digit2)", 
			argNames = "digit1,digit2"
	)
	public void before(int digit1, int digit2) {
		System.out.println("This is Before Advice");
		System.out.println("Arg 1 is %d".formatted(digit1));
		System.out.println("Arg 2 is %d".formatted(digit2));
	}
	
	@After(value = "demoClasses() && args(digit1,digit2)", argNames = "digit1,digit2")
	public void after(int digit1, int digit2) {
		System.out.println("This is After Advice");
		System.out.println("Arg 1 is %d".formatted(digit1));
		System.out.println("Arg 2 is %d".formatted(digit2));
	}

	@AfterReturning(value = "demoClasses()", returning = "result")
	public void afterReturning(int result) {
		System.out.println("This is After Returning Advice");
		System.out.println("Result is %d".formatted(result));
	}
	
	@AfterThrowing(value = "demoClasses()", throwing = "e")
	public void afterThrowing(Exception e) {
		System.out.println("This is After Throwing Advice");
	}
	
}
