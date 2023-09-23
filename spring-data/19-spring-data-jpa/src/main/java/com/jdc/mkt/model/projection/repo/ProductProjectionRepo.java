package com.jdc.mkt.model.projection.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.projection.Inf.ProductProjectionInf;
import com.jdc.mkt.model.projection.record.ProductRecord;
import com.jdc.mkt.model.projection.record.ProductWithCategory;

public interface ProductProjectionRepo extends JpaRepository<Product, Integer>{
	
	//using interface
	List<ProductProjectionInf> findByCategoryName(String category);
	
	//using record class
	List<ProductWithCategory> findByCategoryId(int id);
	
	@Query("""
			select new com.jdc.mkt.model.projection.record.ProductRecord(p.name name,p.detailPrice detailPrice,size(p.saleDetails) as saleDetailCount)
			from Product p where p.name = :name
			""")
	List<ProductRecord> findByProductNameWithDetailCount(@Param("name") String name);
}
