package com.jdc.mkt.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.repo.custom.ProductDynamicSearchRepo;

public interface ProductCustomRepo extends JpaRepository<Product, Integer> ,ProductDynamicSearchRepo{

}
