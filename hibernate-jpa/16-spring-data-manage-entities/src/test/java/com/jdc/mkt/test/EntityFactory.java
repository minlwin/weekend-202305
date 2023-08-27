package com.jdc.mkt.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class EntityFactory {

	static EntityManagerFactory emf;

	@BeforeEach
	 void init() {
		emf = Persistence.createEntityManagerFactory("manage-entities");
	}

	@AfterEach
	 void close() {
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}
}
