package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;


public class FetchTypeTest extends EntityFactory{

	//@Test
	void testFatchWithFind() {
		var em = emf.createEntityManager();
		var p = em.find(Product.class, 6);
		assertNull(p);
		
	}
	//@Test
	void testFatchWithGetReference() {
		var em = emf.createEntityManager();
		var p = em.getReference(Product.class, 6);
		assertNotNull(p);
		assertThrows(EntityNotFoundException.class,()-> p.setName("Other"));
	}
	
	@Test
	void testFatchWithLazyMode() {
		var em = emf.createEntityManager();
		var p = em.getReference(Product.class, 1);
		assertTrue(em.contains(p));
		p.setName("sss");
		em.detach(p);
		p.setName("Hello");
	}
	
	
	
	
	
	
	
	
	
	
	
}
