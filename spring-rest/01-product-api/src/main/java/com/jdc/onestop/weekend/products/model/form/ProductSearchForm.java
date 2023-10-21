package com.jdc.onestop.weekend.products.model.form;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.weekend.products.model.entity.Category_;
import com.jdc.onestop.weekend.products.model.entity.Product;
import com.jdc.onestop.weekend.products.model.entity.Product.Status;
import com.jdc.onestop.weekend.products.model.entity.Product_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class ProductSearchForm {

	private Integer category;
	private String keyword;
	private Integer priceFrom;
	private Integer priceTo;
	private Status status;
	
	public Predicate[] where(CriteriaBuilder cb, Root<Product> root) {
		
		var list = new ArrayList<Predicate>();
		
		if(null != category && category > 0) {
			var category = root.join(Product_.categories, JoinType.LEFT);
			var predicate = cb.equal(category.get(Category_.id), this.category);
			list.add(predicate);
		}
		
		if(StringUtils.hasLength(keyword)) {
			// (lower(p.name) like lower('?%') or lower(p.description) like lower('%?%'))
			var predicate = cb.or(
					cb.like(cb.lower(root.get(Product_.name)), keyword.toLowerCase().concat("%")),
					cb.like(cb.lower(root.get(Product_.description)), "%".concat(keyword.toLowerCase()).concat("%"))
					);
			list.add(predicate);
		}
		
		
		if(null != priceFrom && priceFrom > 0) {
			var predicate = cb.ge(root.get(Product_.price), priceFrom);
			list.add(predicate);
		}
		
		if(null != priceTo && priceTo > 0) {
			var predicate = cb.le(root.get(Product_.price), priceTo);
			list.add(predicate);
		}
		
		if(null != status) {
			var predicate = cb.equal(root.get(Product_.status), status);
			list.add(predicate);
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
