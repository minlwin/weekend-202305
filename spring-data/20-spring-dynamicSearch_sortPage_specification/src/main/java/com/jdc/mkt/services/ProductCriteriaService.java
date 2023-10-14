package com.jdc.mkt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.mkt.entity.Product;
import com.jdc.mkt.model.repo.ProductCriteriaRepo;
import com.jdc.mkt.model.repo.dto.ProductDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Service
public class ProductCriteriaService {

	@PersistenceContext
	EntityManager em;
	@Autowired
	ProductCriteriaRepo repo;

	public List<Product> getProductByCatNameWithCriteria(String name) {
		var criteria = em.getCriteriaBuilder();
		var criteriaQuery = criteria.createQuery(Product.class);
		// select ? from Product p
		var root = criteriaQuery.from(Product.class);
		// select p from Product
		criteriaQuery.select(root);
		// predicate
		criteriaQuery.where(criteria.equal(root.get("category").get("name"), name));
		var query = em.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@Transactional
	public List<ProductDto> getProductByCatNameWithSpecApi(String name) {
		Specification<Product> spec = (root, query, cb) -> {
			root.join("category", JoinType.INNER);
			return cb.equal(root.get("category").get("name"), name);
		};
		//return repo.findAll(spec);
		return repo.findBy(spec, p -> p.as(ProductDto.class).all());
	}

	public long getProductCountByCatName(String name) {
		Specification<Product> spec = (root, cq, cb) -> {
			return cb.equal(root.get("category").get("name"), name);
		};
		return repo.count(spec);
	}
	
	@Transactional
	public long deleteByProductName(String name) {
		Specification<Product> spec = (root,query,cb) -> cb.equal(root.get("name"), name);
		return repo.delete(spec);
	}
	
	
	public List<Product> getCategoryByProductPrice(String name,int price) {
		Specification<Product> specByNameLike = (root,query,cb) -> cb.like(root.get("name"), name.concat("%"));
		Specification<Product> specByPrice = (root,query,cb) -> cb.greaterThanOrEqualTo(root.get("detailPrice"), price);
		specByNameLike = specByNameLike.and(specByPrice);
		
		return repo.findAll(specByNameLike);
	}
	
	
	/* select p.category.name name,count(*) from Product p where
	 * p.category.name = :cname
	 * group by name
	 * ordery by name
	 *  */
	
//	public List<Product> getProductCountByCategoryName(String name){
//		  Specification<Product> spec = (root,query,cb) -> {
//			  query.groupBy(root);
//		  };
//	}
	
	public List<Product> search(String pname,String cname,Integer price){
		List<Specification<Product>> list = new ArrayList<Specification<Product>>();
		
		if(StringUtils.hasLength(cname)) {
			list.add((root,query,cb) -> cb.equal(root.get("category").get("name"), cname));
		}
		
		if(StringUtils.hasLength(pname)) {
			list.add((root,query,cb) -> 
			cb.like(cb.lower(root.get("name")), pname.toLowerCase().concat("%")));
		}
		
		if(null != price && price > 0) {
			list.add((root,query,cb) -> cb.greaterThanOrEqualTo(root.get("detailPrice"), price));
		}
		
		return repo.findAll(Specification.allOf(list));
		
	}
	
	
	public long getAllProductCount() {
		return repo.findAll().size();
	}

}
