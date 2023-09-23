package com.jdc.mkt.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.mkt.entity.Product;

public interface ProductDynamicRepo extends JpaRepository<Product, Integer>{

	<T> T findById(int id,Class<T> type);
}
