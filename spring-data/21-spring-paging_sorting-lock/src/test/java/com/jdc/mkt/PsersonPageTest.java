package com.jdc.mkt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jdc.mkt.entity.Address;
import com.jdc.mkt.entity.Address_;
import com.jdc.mkt.entity.Person;
import com.jdc.mkt.entity.Person_;
import com.jdc.mkt.repo.PersonRepo;

@SpringBootTest
public class PsersonPageTest {

	@Autowired
	PersonRepo repo;
	
	//@Test
	void testWithJpql() {
		var req = PageRequest.of(0, 3);
		var pageable = Pageable.ofSize(3);
		var page = repo.findByAddressWithPage("pansoetan", pageable);
		System.out.println("page size :"+page.getSize());
	}
	
	//@Test
	void testWithDefault() {
		var page = repo.findAll(PageRequest.of(0, 2, Sort.by(Person_.NAME).descending()));
		page.map(p -> p.getName()).forEach(System.out::println);
	}
	
	//@Test
	void testWithSpecification() {
		var page = repo.findAll(
				(root,query,cb) -> cb.equal(root.get(Person_.ADDRESS).get(Address_.STREET), "22st"),
				PageRequest.of(0, 3, Direction.DESC,Person_.NAME));
		
		page.map(p -> p.getName()).forEach(System.out::println);
	}
	
	@Test
	void testWithExample() {
		var person = new Person();
		var address = new Address();
		address.setTownship("pansoetan");
		person.setAddress(address);
		
		var example = Example.of(person,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withIgnorePaths("id","address.id"));
		
		var page = repo.findAll(example, Pageable.ofSize(3));
		
		page.map(p -> p.getName()).forEach(System.out::println);
	}
	
	
}
