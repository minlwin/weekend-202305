package com.jdc.mkt.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Transactional
	void deleteProductByCategoryId(int id);
	Product searchProductByName(String name);
	long countProductByCategoryName(String category);
	boolean existsProductByName(String name);
	List<Product> findTop3ByCategoryName(String category);
	List<Product> findDistinctByNameLikeIgnoreCase(String name);
	
	
	//named query by jpql
	@Query(name = "selectByCategoryNameAndPrice")
	List<Product> selectByCategoryNameAndPrice(@Param("name") String catName,@Param("price")int price);
	
	@Query("select p from Product p where p.detailPrice between :lt and :gt")
	List<Product> selectProductByBetTwoPrice(@Param("gt") int gt_price,@Param("lt")int lt_price);
	
	//named native query
	@Query(nativeQuery = true,name = "selectAllProductByName")
	List<Product> selectProductByNamedLiked(String name);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update product set name = ? ,dt_price = ? where id = ?")
	void updateProductNameAndDtPrice(String name,int price,int id);
	
	
	
}










