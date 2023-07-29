package com.jdc.demo.listener;

import com.jdc.demo.model.ShoppingCart;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ShoppingCartInitializer implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("cart", new ShoppingCart());
	}
}
