package com.jdc.onestop.weekend.products.model.repo;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepositoryImplementation<T, ID>{

	long count(Function<CriteriaBuilder, CriteriaQuery<Long>> queryFunc);
	
	<R> List<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc);

	<R> Page<R> findAll(
			Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, 
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, 
			int page, int size);

}
