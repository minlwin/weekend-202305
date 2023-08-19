package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.entity.Course;
import com.jdc.mkt.repo.CourseRepo;

//@SpringJUnitConfig(locations = "/app-entity.xml")
@SpringJUnitConfig(classes = ApplicationConfig.class)
public class CourseTest {

	@Autowired
	private CourseRepo repo;

	@ParameterizedTest
	@CsvSource(value = "Java Basic:300000", delimiter = ':')
	void test1(String name, int fees) {
		var course = repo.create(new Course(name, fees));
		assertEquals(300000, course.getFees());
	}
}
