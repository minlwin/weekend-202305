package com.jdc.onestop.weekend.products.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.weekend.products.model.form.CategoryEditForm;
import com.jdc.onestop.weekend.products.model.output.CategoryDto;
import com.jdc.onestop.weekend.products.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryApi {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	public List<CategoryDto> getAll() {
		return service.findAll();
	}
	
	@PostMapping
	CategoryDto create(@Validated @RequestBody CategoryEditForm form, BindingResult result) {
		return service.create(form);
	}
	
	@PutMapping("{id}")
	CategoryDto update(@PathVariable int id, @Validated @RequestBody CategoryEditForm form, BindingResult result) {
		return service.update(id, form);
	}
}
