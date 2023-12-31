package com.jdc.mkt.model.repo.non_null;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.jdc.mkt.entity.Product;

public interface ProductNonNullRepo extends JpaRepository<Product, Integer>{

	
	@Query("select p from Product p where p.name like ?1")
	List<Product> findByProductNameLikeSearch(@NonNull String name);
}
