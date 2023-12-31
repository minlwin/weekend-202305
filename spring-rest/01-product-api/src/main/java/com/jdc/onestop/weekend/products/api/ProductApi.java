package com.jdc.onestop.weekend.products.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.weekend.products.model.form.ProductEditForm;
import com.jdc.onestop.weekend.products.model.form.ProductSearchForm;
import com.jdc.onestop.weekend.products.model.output.ProductDetailsDto;
import com.jdc.onestop.weekend.products.model.output.ProductDto;
import com.jdc.onestop.weekend.products.model.output.ProductUploadResult;
import com.jdc.onestop.weekend.products.model.output.SaveResult;
import com.jdc.onestop.weekend.products.service.ImageFileWriter;
import com.jdc.onestop.weekend.products.service.ProductService;
import com.jdc.onestop.weekend.products.service.TextFileReader;

@RestController
@RequestMapping("products")
public class ProductApi {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private TextFileReader textFileReader;
	
	@Autowired
	private ImageFileWriter fileWriter;

	@GetMapping
	Page<ProductDto> search(
			ProductSearchForm form, 
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@PostMapping("upload")
	ProductUploadResult upload(@RequestParam MultipartFile file) {
		var lines = textFileReader.read(file);
		return service.create(lines);
	}
	
	@GetMapping("{id}")
	ProductDetailsDto findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping("{id}/photos")
	ProductDetailsDto uploadPhotos(@PathVariable int id, @RequestParam MultipartFile[] files) {
		
		var images = fileWriter.save(id, files);
		service.savePhoto(id, images);
		
		return service.findById(id);
	}
	
	@PostMapping
	SaveResult create(@Validated @RequestBody ProductEditForm form, BindingResult result) {
		return service.create(form);
	}
	
	@PutMapping("{id}")
	SaveResult update(@PathVariable int id, @Validated @RequestBody ProductEditForm form, BindingResult result) {
		return service.update(id, form);
	}
	
}
