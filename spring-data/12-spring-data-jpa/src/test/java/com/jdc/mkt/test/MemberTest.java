package com.jdc.mkt.test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.jdc.mkt.entity.Member;

public class MemberTest {

	private static EntityManagerFactory emf;

	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("SPRING-DATA");
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/member-values.properties", delimiter = ':')
	void test1(String name, String dob, String loginId, String password, String email, int id) throws ParseException {
		
		var df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = df.parse(dob);
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var member = new Member(name, d, loginId, password, email);
		em.persist(member);
		System.out.println("Member id : %d".formatted(member.getId()));
		em.getTransaction().commit();
		em.close();
		assertThat(member, allOf(hasProperty("id", is(id)), hasProperty("name", is(name))));

	}

	@AfterAll
	static void close() {
		if (null != emf || emf.isOpen()) {
			emf.close();
		}
	}

}
