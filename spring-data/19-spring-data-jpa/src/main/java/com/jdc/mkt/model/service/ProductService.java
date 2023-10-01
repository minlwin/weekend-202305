package com.jdc.mkt.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.jdc.mkt.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductService {
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Product> dynamicWithJpqlSearch(int id, String pName, String cName, int dtPrice) {

		var sb = new StringBuilder("select p from Product p where 1=1");
		Map<String, Object> map = new HashMap<String, Object>();

		if (id > 0) {
			sb.append(" and p.id = :id");
			map.put("id", id);
		}
		if (null != pName && !pName.isEmpty()) {
			sb.append(" and p.name like :pname");
			map.put("pname", pName.concat("%"));
		}	
		if (null != cName && !cName.isEmpty()) {
			sb.append(" and p.category.name = :cname");
			map.put("cname", cName);
		}
		if (dtPrice > 0) {
			sb.append(" and p.detailPrice <= :price");
			map.put("price", dtPrice);
		}
		
		var query = em.createQuery(sb.toString());
		for (Entry<String,Object> p : map.entrySet()) {
			query.setParameter(p.getKey(), p.getValue());
		}
		
		return query.getResultList();
		
	}
}
