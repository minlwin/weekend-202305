package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

@TestMethodOrder(OrderAnnotation.class)
public class ManipulateEntityTest extends EntityFactory {

	//@Test
	@Order(1)
	void insertCategory() {
		// transient state
		var cat = new Category("Snacks");

		var em = emf.createEntityManager();
		em.getTransaction().begin();
		// to be managed state
		em.persist(cat);
		assertTrue(em.contains(cat));
		em.getTransaction().commit();

		em = emf.createEntityManager();
		// to be manage state
		var cateogry = em.find(Category.class, 3);
		assertTrue(em.contains(cateogry));
		// to be detached state from manage
		em.detach(cateogry);
		assertFalse(em.contains(cateogry));
		// to be managed state from merge
		var categoryUpdate = em.merge(cateogry);
		assertTrue(em.contains(categoryUpdate));

	}

	@Order(2)
	//@ParameterizedTest
	@CsvSource("Sunflower seeds ,2000,Snacks")
	void insertProduct(String pName, int price, String cName) {

		// to be transient state
		var product = new Product(pName, price);
		var category = new Category(cName);
		product.setCategory(category);
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(category);		
		em.persist(product);
		em.getTransaction().commit();

	}
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints=6)
	void find_method(int id) {
		var em = emf.createEntityManager();
		var product = em.find(Product.class, id);
		//assertEquals("Lemon", product.getName());
		assertNull(product);
	}
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(ints=6)
	void reference_method(int id) {
		var em = emf.createEntityManager();
		var product = em.getReference(Product.class, id);
		//assertEquals("Lemon", product.getName());
		//assertThrows(EntityNotFoundException.class,()-> em.getReference(Product.class, id));
	}
	
	
	
	
	
	
}
