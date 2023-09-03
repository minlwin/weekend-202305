package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

public class RemoveTest extends EntityFactory {

	//@Test
	void testRemove() {
		var em = emf.createEntityManager();
		var c = em.find(Category.class, 2);
		em.getTransaction().begin();
		em.remove(c);
		//em.persist(c);
		//assertThrows(IllegalArgumentException.class, () -> em.merge(c));
		em.getTransaction().commit();
		

	}
	
	//@Test
	void testRemoveWithCascade() {
		var em = emf.createEntityManager();
		var p = em.find(Product.class, 2);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		

	}
	
	@Test
	void testPersist() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var c = new Category("Other");
		var p = new Product("Watermelon", 1000);
		p.setCategory(c);
		em.persist(p);
		em.getTransaction().commit();
		

	}
}
