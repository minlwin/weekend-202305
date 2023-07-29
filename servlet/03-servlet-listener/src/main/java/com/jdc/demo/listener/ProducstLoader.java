package com.jdc.demo.listener;

import java.util.List;

import com.jdc.demo.model.Product;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ProducstLoader implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		var applicationScope = sce.getServletContext();
		
		applicationScope.setAttribute("products", getAllProducts());
	}

	private List<Product> getAllProducts() {
		return List.of(
			new Product("Coke", 1000),
			new Product("Nest Cafe", 1500),
			new Product("Seven Up", 1300),
			new Product("Pepsi", 1200),
			new Product("Cream Soda", 900)
		);
	}
}
