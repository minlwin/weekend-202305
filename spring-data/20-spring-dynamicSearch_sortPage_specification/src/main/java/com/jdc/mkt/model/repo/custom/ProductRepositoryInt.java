package com.jdc.mkt.model.repo.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.repo.BaseRepositoryInt;

public interface ProductRepositoryInt extends JpaRepository<Product, Integer>,BaseRepositoryInt{

	
	@Query("select p from Product p where p.name like ?1")
	List<Product> findByProductNameLikeSearch(@NonNull String name);
}
