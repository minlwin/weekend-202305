package com.jdc.mkt.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdc.mkt.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepoImpl {
	
	@PersistenceContext
	private EntityManager em;

	
	public Product create(Product t) {	
		return null;
	}

	
	public List<Product> selectBy(Product t) {
		return null;
	}

	
	public Product findById(int id) {
		return em.find(Product.class, id);
	}

}
