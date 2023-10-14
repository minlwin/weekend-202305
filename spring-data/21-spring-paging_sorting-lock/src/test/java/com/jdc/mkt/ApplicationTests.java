package com.jdc.mkt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.mkt.entity.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
class ApplicationTests {
	
	@PersistenceContext
	EntityManager em;
	

	@Test
	void contextLoads() {
		var p = em.find(Person.class, 1);
		
	}

}
