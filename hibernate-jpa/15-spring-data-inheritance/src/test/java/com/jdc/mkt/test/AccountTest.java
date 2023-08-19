package com.jdc.mkt.test;

import java.awt.Color;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Account;
import com.jdc.mkt.entity.Student;

public class AccountTest {

	static EntityManagerFactory emf;
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("INHERITANCE");
	}
	
	@AfterAll
	static void close() {
		if(emf != null && emf.isOpen()) {
			emf.close();
		}
	}
	@ParameterizedTest
	@CsvSource("William,william,123")
	void test1(String name,String loginId,String password) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		Account acc = new Student(name, loginId, password);
		acc.setColor(Color.darkGray);
		em.persist(acc);
		em.getTransaction().commit();
		
		var a = em.find(Account.class, 1);
		System.out.println(a.getColor());
	}
}
