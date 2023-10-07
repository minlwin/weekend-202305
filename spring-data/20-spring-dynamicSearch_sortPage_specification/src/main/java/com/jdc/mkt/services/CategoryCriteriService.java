package com.jdc.mkt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.model.repo.CategoryCriteriaRepo;

@Service
public class CategoryCriteriService {
	
	@Autowired
	CategoryCriteriaRepo repo;

	@Transactional
	public long deleteCategoryByName(String name) {
		return repo.delete((root,query,cb) -> cb.equal(root.get("name"), name));
	}
	
	
}
