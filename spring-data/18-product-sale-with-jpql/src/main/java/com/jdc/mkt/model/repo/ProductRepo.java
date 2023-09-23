package com.jdc.mkt.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.mkt.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	//query method
	Product findByName(String name);
}
