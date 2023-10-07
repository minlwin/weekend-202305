package com.jdc.mkt.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.mkt.entity.Category;

public interface CategoryCriteriaRepo extends JpaRepositoryImplementation<Category, Integer> {

	
}
