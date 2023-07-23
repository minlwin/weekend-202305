package com.jdc.demo.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ProductAddLogger implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {

	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {

	}
}
