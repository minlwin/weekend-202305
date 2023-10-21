package com.jdc.onestop.weekend.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.onestop.weekend.products.model.entity.Product;
import com.jdc.onestop.weekend.products.model.entity.Product_;
import com.jdc.onestop.weekend.products.model.form.ProductSearchForm;
import com.jdc.onestop.weekend.products.model.output.ProductDto;
import com.jdc.onestop.weekend.products.model.output.ProductUploadResult;
import com.jdc.onestop.weekend.products.model.repo.ProductRepo;
import com.jdc.onestop.weekend.products.utils.exceptions.ApiBusinessException;
import com.jdc.onestop.weekend.products.utils.exceptions.ApiValidationException;

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
					query.select(cb.count(root.get(Product_.ID)));
					query.where(form.where(cb, root));
					return query;
				};
		
		return repo.findAll(queryFunc, countFunc, page, size);
	}

	public ProductUploadResult create(List<String> lines) {
		
		var messages = new ArrayList<String>();
		var products = new ArrayList<Product>();
		
		for(var i = 0; i < lines.size() ; i ++) {
			var line = lines.get(i);
			var array = line.split("\t");
			
			if(array.length < 4) {
				messages.add("Line No. %d : Ivalid column size.".formatted(i + 1));
				continue;
			}
			
			var product = convertArrayToProduct(array);
			products.add(product);
			
		}
		
		if(!messages.isEmpty()) {
			throw new ApiValidationException(messages);
		}
		
		
		
		return new ProductUploadResult(lines.size(), "Upload success!");
	}

	private Product convertArrayToProduct(String[] array) {
		
		if(!StringUtils.hasLength(array[0])) {
			throw new ApiBusinessException("Please enter product name.");
		}
		
		try {
			
			var product = new Product();
			product.setName(array[0]);
			product.setPrice(Integer.parseInt(array[1]));
			product.setDescription(array[2]);
			
			var catagories = array[3].split(",");
			
		} catch (NumberFormatException e) {
			throw new ApiBusinessException("Please enter price with digit.");
		}
		
		return null;
	}

}
