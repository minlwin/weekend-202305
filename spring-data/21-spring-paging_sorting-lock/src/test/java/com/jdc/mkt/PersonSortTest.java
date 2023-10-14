package com.jdc.mkt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;

import com.jdc.mkt.entity.Address_;
import com.jdc.mkt.entity.Person;
import com.jdc.mkt.entity.Person_;
import com.jdc.mkt.repo.PersonRepo;

@SpringBootTest
public class PersonSortTest {

	@Autowired
	PersonRepo repo;
	
	@Test
	void test() {
		//var list = repo.findByAddressTownshipOrderByNameAsc("pansoetan");
		//var list = repo.findByAddress("pansoetan",Sort.by(Person_.NAME));
		//var list = repo.findByAddress("pansoetan",Sort.by(Order.desc(Person_.NAME)));
		//var list = repo.findByAddress("pansoetan",TypedSort.by(Person_.NAME).descending());
		
		var typeSort = TypedSort.sort(Person.class);
		var list = repo.findByAddress("pansoetan",typeSort.by(Person::getName)
				.and(typeSort.by(Person::getAge)) .descending());
		
		list.stream()
		.map(p -> p.getName())
		.forEach(System.out::println);
	}
	
	//@Test
	void testWithExample() {
		var root = Example.of(new Person());		
		var sort = Sort.by(Person_.NAME).descending();
		var list = repo.findAll(root,sort);
		list.stream()
		.map(p -> p.getName())
		.forEach(System.out::println);
	}
	//@Test
	void testWithSpecification() {
		var list = repo.findAll(
				(root,query,sb) -> sb.equal(root.get(Person_.address).get(Address_.township), "pansoetan"),
				Sort.by(Person_.NAME));
		list.stream().map(p -> p.getName()).forEach(System.out::println);
	}
}








