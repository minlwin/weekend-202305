package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.ProductDto;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest extends FactoryEmf {

	// @Test
	@Order(1)
	void testPersist() {

		var p1 = new Product("Mango", 1000);
		var p2 = new Product("Orange", 1500);
		var p3 = new Product("PileApple", 2500);
		var c = new Category("Fruits");
		c.addProduct(p1, p2, p3);

		var em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();
	}

	// @Test
	@Order(2)
	void testStaticQuery() {
		var em = emf.createEntityManager();

		var query_count = em.createQuery("select count(p) from Product p ", Long.class);
		var count = query_count.getSingleResult();
		System.out.println(count);

		var findAll = em.createQuery("select p from Product p", Product.class);
		var list = findAll.getResultList();
		list.forEach(p -> System.out.println(p.getName()));

		var findByName = em.createQuery("select p from Product p where p.name = ?1", Product.class);
		findByName.setParameter(1, "Orange");
		var orange = findByName.getSingleResult();
		System.out.println(orange.getName());

		em.getTransaction().begin();
		var update = em.createQuery("update Product p set p.name = ?1 ,p.price = ?2 where  p.id = ?3");
		update.setParameter(1, "apple");
		update.setParameter(2, 2000);
		update.setParameter(3, 3);
		var row = update.executeUpdate();
		em.getTransaction().commit();
		System.out.println(row);
	}

	// @Test
	@Order(3)
	void testNamedQuery() {
		var em = emf.createEntityManager();
		var findByPrice = em.createNamedQuery("findByPrice", Product.class);
		findByPrice.setParameter("gt_price", 1000);
		findByPrice.setParameter("lt_price", 3000);

		var list = findByPrice.getResultList();
		for (Product p : list) {
			System.out.println(p.getName() + "\t" + p.getSize() + "\t" + p.getPrice());
		}
	}

	@Test
	@Disabled
	@Order(4)
	void testOperator() {
		var em = emf.createEntityManager();
		var bt_query = em.createQuery("select p from Product p where p.price  between  :lt_price and :gt_price",
				Product.class);

		bt_query.setParameter("lt_price", 1000);
		bt_query.setParameter("gt_price", 2000);

		assertEquals(3, bt_query.getResultList().size());

		var n_query = em.createQuery("select p from Product p where  p.category.id  is  null");
		assertEquals(1, n_query.getResultList().size());

		var like_query = em.createQuery("select p from Product p where lower(p.name) like lower(:name)");
		like_query.setParameter("name", "p".concat("%"));

		assertEquals(2, like_query.getResultList().size());
	}

	@Test
	@Order(5)
	void testJoinFromProdcut() {
		var em = emf.createEntityManager();
//		var query1 = em.createQuery("select p from Product p where p.name = :name ", Product.class);
//		query1.setParameter("name", "Lemon");
//		var list = query1.getResultList();
//		list.forEach(p-> 
//		System.out.println(" product Id :%d product Name :%s category :%s".formatted(p.getId(), 
//				p.getName(),p.getCategory() != null ? p.getCategory().getName():"No cat")) );

		var proj1 = em.createQuery("select new com.jdc.mkt.entity.ProductDto(p.name,p.price,p.category.name) from Product p ",ProductDto.class);
		var list1 = proj1.getResultList();
		list1.forEach(product ->System.out.println( product.name() +"\t"+ product.price()+"\t"+product.cat()));
	
	}

	//@Test
	@Order(6)
	void testJoinFromCategory() {
		var em = emf.createEntityManager();
		var query2 = em.createQuery("select c from Category c where c.id = 1", Category.class);
		var cat = query2.getSingleResult();
		assertEquals("Fruits", cat.getName());
		assertEquals(4, cat.getProducts().size());

		var query3 = em.createQuery("select c.name,c.products.name ");

	}
	

	

}
