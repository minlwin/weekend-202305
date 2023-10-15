package com.jdc.onestop.weekend.products.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.onestop.weekend.products.model.entity.Product;
import com.jdc.onestop.weekend.products.model.entity.Product_;
import com.jdc.onestop.weekend.products.model.form.ProductSearchForm;
import com.jdc.onestop.weekend.products.model.output.ProductDto;
import com.jdc.onestop.weekend.products.model.repo.ProductRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;

	public Page<ProductDto> search(ProductSearchForm form, int page, int size) {
		
		Function<CriteriaBuilder, CriteriaQuery<ProductDto>> queryFunc = 
				cb -> {
					var query = cb.createQuery(ProductDto.class);
					var root = query.from(Product.class);
					ProductDto.select(query, root);
					query.where(form.where(cb, root));
					return query;
				};
				
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = 
				cb -> {
					var query = cb.createQuery(Long.class);
					var root = query.from(Product.class);
					query.select(root.get(Product_.ID));
					query.where(form.where(cb, root));
					return query;
				};
		
		return repo.findAll(queryFunc, countFunc, page, size);
	}

}
