package com.jdc.mkt.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Address;
import com.jdc.mkt.entity.Customer;
import com.jdc.mkt.entity.Customer.CustomerType;

public class CustomerTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	@ParameterizedTest
	@CsvSource(value = "Andrew:Gold:22st:lathar:yangon",delimiter = ':')
	void test1(String name,String type,String street,String township,String city) {
		em.getTransaction().begin();
		var address = new Address(street, township, city);
		em.persist(address);
		System.out.println("Address id : %d".formatted(address.getId()));
		var customer = new Customer(name, CustomerType.valueOf(type.toUpperCase()),address);
		em.persist(customer);
		System.out.println("Customer id : %d".formatted(customer.getId()));
		em.getTransaction().commit();
	}

	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("jpa-relation");
	}
	@BeforeEach
	void createEntityManager() {
		em = emf.createEntityManager();
	}
	@AfterEach
	void closeEntityManager() {
		if (null != em || em.isOpen()) {
			em.close();
		}
	}	
	@AfterAll
	static void close() {
		if (null != emf || emf.isOpen()) {
			emf.close();
		}
	}
}
