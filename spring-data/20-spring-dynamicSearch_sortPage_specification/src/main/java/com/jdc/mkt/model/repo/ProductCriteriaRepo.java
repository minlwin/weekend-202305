package com.jdc.mkt.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.mkt.entity.Product;

public interface ProductCriteriaRepo extends JpaRepositoryImplementation<Product, Integer>{

}
