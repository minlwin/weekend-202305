package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Account;
import com.jdc.mkt.entity.Teacher;

public class AccountTest extends EmfCreation {

	@Test
	void test1() {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		Account acc = new Teacher("mkt","mkt","123");
		em.persist(acc);
		em.getTransaction().commit();
	}
}
