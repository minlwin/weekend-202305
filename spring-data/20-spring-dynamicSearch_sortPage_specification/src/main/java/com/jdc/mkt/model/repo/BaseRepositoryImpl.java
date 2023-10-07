package com.jdc.mkt.model.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.jdc.mkt.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseRepositoryImpl implements BaseRepositoryInt {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> search(int id, String pname, String cname, int price) {
		
		StringBuilder sb = new StringBuilder("select p from Product p where 1=1");
		Map<String, Object> map = new HashMap<String, Object>();
		if (id > 0) {
			sb.append(" and p.id = :id");
			map.put("id", id);
		}

		if (StringUtils.hasLength(cname)) {
			sb.append(" and p.category.name = :name");
			map.put("cname", cname);
		}
		if (StringUtils.hasLength(pname)) {
			sb.append(" and lower(p.name) like lower(:pname)");
			map.put("pname", pname);
		}
		
		if(price > 0) {
			sb.append(" and p.detailPrice =< :price");
			map.put("price", price);
		}

		var query = em.createQuery(sb.toString());
		
		for(var entry : map.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}

}
