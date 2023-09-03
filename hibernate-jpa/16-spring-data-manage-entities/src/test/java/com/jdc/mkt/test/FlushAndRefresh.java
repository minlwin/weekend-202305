package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

public class FlushAndRefresh extends EntityFactory{

	@Test
	void testPersist() {
		
		Category c = new Category("test");	
		Product p = new Product("test_product", 200);
		p.setCategory(c);
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p);
		em.refresh(p);
		p.setPrice(400);
		em.getTransaction().commit();
		
	}
}
