package com.jdc.balance.web;

import java.util.EnumSet;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import jakarta.servlet.DispatcherType;

public class ApplicationLoaderForSecurity extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
		return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC);
	}
}
