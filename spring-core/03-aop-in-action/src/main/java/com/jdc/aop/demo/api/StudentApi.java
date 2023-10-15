package com.jdc.aop.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.aop.demo.entity.Student;
import com.jdc.aop.demo.model.StudentForm;
import com.jdc.aop.demo.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentApi {
	
	@Autowired
	private StudentService service;

	@GetMapping
	List<Student> search(@RequestParam Optional<String> keyword) {
		return service.search(keyword);
	}
	
	@PostMapping
	int create(@Validated @RequestBody StudentForm form, BindingResult result) {
		return service.create(form);
	}
	
	@PutMapping("{id}")
	int update(@PathVariable int id, @Validated @RequestBody StudentForm form, BindingResult result) {
		return service.update(id, form);
	}
}
