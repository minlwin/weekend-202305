package com.jdc.mkt.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.mkt.model.dto.ProductByTownshipAndDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SaleDetailService {
	
	@PersistenceContext
	EntityManager em;
	
	public List<ProductByTownshipAndDate> getProductByTownshipAndDate(String township,LocalDate date){		
		var query = em.createQuery("""
				select new com.jdc.mkt.model.dto.ProductByTownshipAndDate(sd.product.name,sd.product.detailPrice,sum(sd.qty)) 
				from SaleDetails sd where 
				sd.sales.customer.address.township = :township and
				sd.sales.saleDate = :date group by sd.product.name,sd.product.detailPrice
				""",ProductByTownshipAndDate.class);
		
		query.setParameter("township", township);
		query.setParameter("date", date);
		return query.getResultList();
	}
	
	public long getQtyByProductBetTwoDate(String product,LocalDate from_dt,LocalDate to_dt) {
		var query = em.createQuery("""
				select sum(sd.qty) from SaleDetails sd 
				where sd.product.name = :name  and
				sd.sales.saleDate between :from and :to			
				""",Long.class);
			query.setParameter("name", product);
			query.setParameter("from", from_dt);
			query.setParameter("to", to_dt);
			
		return query.getSingleResult();
	}
}












