package com.jdc.onestop.weekend.products.model.repo;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> 
	implements BaseRepository<T, ID>{

	private EntityManager em;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public long count(Function<CriteriaBuilder, CriteriaQuery<Long>> queryFunc) {
		var criteriaQuery = queryFunc.apply(em.getCriteriaBuilder());
		return em.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public <R> List<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var criteriaQuery = queryFunc.apply(em.getCriteriaBuilder());
		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public <R> Page<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		var count = em.createQuery(countFunc.apply(em.getCriteriaBuilder()))
				.getSingleResult();
		var content = em.createQuery(queryFunc.apply(em.getCriteriaBuilder()))
				.setFirstResult(page * size)
				.setMaxResults(size)
				.getResultList();
		return new PageImpl<R>(content, PageRequest.of(page, size), count);
	}

}
