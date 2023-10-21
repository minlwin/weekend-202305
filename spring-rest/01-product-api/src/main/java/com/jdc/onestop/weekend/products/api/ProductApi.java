package com.jdc.onestop.weekend.products.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.weekend.products.model.form.ProductEditForm;
import com.jdc.onestop.weekend.products.model.form.ProductSearchForm;
import com.jdc.onestop.weekend.products.model.output.ProductDetailsDto;
import com.jdc.onestop.weekend.products.model.output.ProductDto;
import com.jdc.onestop.weekend.products.model.output.SaveResult;
import com.jdc.onestop.weekend.products.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductApi {
	
	@Autowired
	private ProductService service;

	@GetMapping
	Page<ProductDto> search(
			ProductSearchForm form, 
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@PostMapping("upload")
	Page<ProductDto> upload(@RequestParam MultipartFile file) {
		return null;
	}
	
	@GetMapping("{id}")
	ProductDetailsDto findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping("photos")
	ProductDetailsDto uploadPhotos(@RequestParam MultipartFile[] files) {
		return null;
	}
	
	@PostMapping
	SaveResult create(@RequestBody ProductEditForm form) {
		return null;
	}
	
	@PutMapping
	SaveResult update(@RequestBody ProductEditForm form) {
		return null;
	}
	
}
