package com.jdc.onestop.weekend.products.model.repo;

import java.util.Optional;

import com.jdc.onestop.weekend.products.model.entity.Category;

public interface CategoryRepo extends BaseRepository<Category, Integer>{

	Optional<Category> findOneByNameIgnoreCase(String name);
}
