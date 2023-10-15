package com.jdc.onestop.weekend.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.weekend.products.model.entity.Category;
import com.jdc.onestop.weekend.products.model.form.CategoryEditForm;
import com.jdc.onestop.weekend.products.model.output.CategoryDto;
import com.jdc.onestop.weekend.products.model.repo.CategoryRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepo repo;

	@Transactional(readOnly = true)
	public List<CategoryDto> findAll() {
		return repo.findAll().stream().map(CategoryDto::new).toList();
	}

	public CategoryDto create(CategoryEditForm form) {
		var entity = new Category();
		entity.setName(form.getName());
		entity = repo.save(entity);
		return new CategoryDto(entity);
	}

	public CategoryDto update(int id, CategoryEditForm form) {
		var entity = repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		entity.setName(form.getName());
		return new CategoryDto(entity);
	}

}
