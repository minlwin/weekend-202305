package com.jdc.mkt.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.entity.SaleDetails;
import com.jdc.mkt.entity.SaleDetailsPk;

public interface SaleDetailsRepo extends JpaRepository<SaleDetails, SaleDetailsPk>{

	@Transactional
	void deleteProductByProductId(int id);
	
	List<SaleDetails> findByProductNameIgnoreCaseAndSalesId(String pName,int id);
}
