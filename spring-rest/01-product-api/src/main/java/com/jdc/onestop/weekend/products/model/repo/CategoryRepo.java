package com.jdc.onestop.weekend.products.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.onestop.weekend.products.model.entity.Category;

public interface CategoryRepo extends JpaRepositoryImplementation<Category, Integer>{

}
