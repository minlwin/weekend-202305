package com.jdc.mkt.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdc.mkt.entity.Sales;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SaleRepoImpl{

	@PersistenceContext
	private EntityManager em;

	public Sales create(Sales sale) {
		em.persist(sale);
		return sale;
	}

	public List<Sales> selectBy(Sales t) {
		return null;
	}

	public Sales findById(int id) {
		return null;
	}
}
