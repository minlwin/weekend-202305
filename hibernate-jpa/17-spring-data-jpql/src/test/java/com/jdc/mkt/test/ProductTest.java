package com.jdc.mkt.test;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.ProductDto;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest extends FactoryEmf {

	//@Test
	@Order(1)
	void testPersist() {
		
		var p1 = new Product("Mango", 1000);
		var p2 = new Product("Orange", 1500);
		var p3 = new Product("PileApple", 2500);
		var c = new Category("Fruits");
		c.addProduct(p1,p2,p3);
		
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();
	}
	
	//@Test
	@Order(2)
	void testStaticQuery() {
		var em = emf.createEntityManager();
		
		var query_count = em.createQuery("select count(p) from Product p ",Long.class);
		 var count = query_count.getSingleResult();
		 System.out.println(count);
		
		var findAll = em.createQuery("select p from Product p",Product.class);
		var list = findAll.getResultList();
		list.forEach(p -> System.out.println( p.getName()));
		
		var findByName = em.createQuery("select p from Product p where p.name = ?1",Product.class);
		findByName.setParameter(1, "Orange");
		var orange = findByName.getSingleResult();
		System.out.println(orange.getName());
		
		em.getTransaction().begin();
		var update  = em.createQuery("update Product p set p.name = ?1 ,p.price = ?2 where  p.id = ?3");
		update.setParameter(1, "apple");
		update.setParameter(2, 2000);
		update.setParameter(3,3);
		var row =  update.executeUpdate();
		em.getTransaction().commit();
		System.out.println(row);
	}
	
	//@Test
	@Order(3)
	void testNamedQuery() {
		var em = emf.createEntityManager();
		var findByPrice = em.createNamedQuery("findByPrice",Product.class);
		findByPrice.setParameter("gt_price", 1000);
		findByPrice.setParameter("lt_price", 3000);
		
		var list =  findByPrice.getResultList();
		for(Product p :list) {
			System.out.println(p.getName()+"\t"+p.getSize()+"\t"+p.getPrice());
		}
	}
	
	@Test
	void testJoinQuery() {
		var em = emf.createEntityManager();
		var selectByCategoryName = em.createQuery("""
				select new com.jdc.mkt.entity.ProductDto(p.name,p.price,p.size,c.name )
				from Product p join p.category c where p.category.name = :name
				""",ProductDto.class);
		selectByCategoryName.setParameter("name", "Fruits");
		var list = selectByCategoryName.getResultList();
		System.out.println(list);
	}
	
}












