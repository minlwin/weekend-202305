package com.jdc.mkt.model.service;

import org.springframework.stereotype.Service;

import com.jdc.mkt.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductService {

	@PersistenceContext
	EntityManager em;
	
	public  Product findById(int id) {
		return em.find(Product.class, id);
	}
}
