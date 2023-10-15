package com.jdc.aop.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.aop.demo.entity.Student;
import com.jdc.aop.demo.model.StudentForm;
import com.jdc.aop.demo.repo.StudentRepo;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	private StudentRepo repo;

	@Transactional(readOnly = true)
	public List<Student> search(Optional<String> keyword) {
		
		Specification<Student> spec = keyword.filter(StringUtils::hasLength).isPresent() ?
				(root, query, cb) -> cb.or(
					cb.like(cb.lower(root.get("name")), keyword.get().toLowerCase().concat("%")),
					cb.like(cb.lower(root.get("phone")), keyword.get().toLowerCase().concat("%")),
					cb.like(cb.lower(root.get("email")), keyword.get().toLowerCase().concat("%"))
				) 
				: Specification.where(null);
		
		return repo.findAll(spec);
	}
	
	public int create(StudentForm form) {
		// get entity from form
		Student entity = form.entity();
		
		// save entity
		entity = repo.save(entity);
		
		// return generated id
		return entity.getId();
	}
	
	public int update(int id, StudentForm form) {
		// find entity by id
		return repo.findById(id).map(entity -> {
			entity.setName(form.name());
			entity.setPhone(form.phone());
			entity.setEmail(form.email());
			return entity.getId();
		}).orElse(0);
	}
}
