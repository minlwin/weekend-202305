package com.jdc.mkt.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class EmfCreation {

	 static EntityManagerFactory emf;

	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("SPRING-DATA");
	}
	
	@AfterAll
	static void close() {
		if (null != emf || emf.isOpen()) {
			emf.close();
		}
	}
}
