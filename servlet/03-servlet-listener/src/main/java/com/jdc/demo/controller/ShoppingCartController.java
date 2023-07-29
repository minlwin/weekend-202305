package com.jdc.demo.controller;

import java.io.IOException;
import java.util.List;

import com.jdc.demo.model.Product;
import com.jdc.demo.model.ShoppingCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/cart/add",
		"/cart/remove",
		"/cart/clear"
})
public class ShoppingCartController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var id = req.getParameter("id");
		var cart = (ShoppingCart)req.getSession(true).getAttribute("cart");
		@SuppressWarnings("unchecked")
		var products = (List<Product>)getServletContext().getAttribute("products");
		
		switch(req.getServletPath()) {
		case "/cart/add" -> cart.addToCart(findProduct(id, products));
		case "/cart/remove" -> cart.removeFromCart(Integer.parseInt(id));
		case "/cart/clear" -> cart.clear();
		default -> throw new IllegalArgumentException();
		}
		
		resp.sendRedirect("/");
	}

	private Product findProduct(String id, List<Product> products) {
		for(var product : products) {
			if(Integer.parseInt(id) == product.getId()) {
				return product;
			}
		}
		return null;
	}

}
