package com.jdc.onestop.weekend.products.utils.exceptions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationHandlerAspect {

	@Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController)")
	public void apiMethod() {}
	
	@Before(value = "apiMethod() and args(..,result)", argNames = "result")
	public void handle(BindingResult result) {
		if(result.hasErrors()) {
			throw new ApiValidationException(result);
		}
	}
}
