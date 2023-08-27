package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;

public class ManageEntitiesTest extends EntityFactory{

	@Test
	void testUpdate() {
		var em = emf.createEntityManager();
		//to be managed
		var p = em.find(Product.class, 1);
		em.getTransaction().begin();
		//update query 
		p.setName("Other");
		em.getTransaction().commit();
		
		assertTrue(em.contains(p));
		//to be detached
		em.detach(p);
		em.detach(p.getCategory());
		
		assertFalse(em.contains(p));
		assertFalse(em.contains(p.getCategory()));
		
		//to be managed
		var newp = em.merge(p);
		
		em.getTransaction().begin();
		//update query
		newp.setName("Durian");
		//em.persist(newp);
		
		em.getTransaction().commit();
		//to be detached
		em.clear();
		
		assertThrows(PersistenceException.class, () ->em.persist(newp));
		
		//to be managed
		var newp2 =  em.merge(p);
		
		
	}
}
