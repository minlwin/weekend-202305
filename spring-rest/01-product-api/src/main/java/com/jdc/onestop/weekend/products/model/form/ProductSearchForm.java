package com.jdc.onestop.weekend.products.model.form;

import java.util.ArrayList;
import java.util.Optional;

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

	private Optional<Integer> category;
	private Optional<String> keyword;
	private Optional<Integer> priceFrom;
	private Optional<Integer> priceTo;
	private Optional<Status> status;
	
	public Predicate[] where(CriteriaBuilder cb, Root<Product> root) {
		
		var list = new ArrayList<Predicate>();
		
		category.filter(a -> a > 0).ifPresent(param -> {
			var category = root.join(Product_.categories, JoinType.LEFT);
			var predicate = cb.equal(category.get(Category_.id), param);
			list.add(predicate);
		});
		
		keyword.filter(StringUtils::hasLength).ifPresent(param -> {
			// (lower(p.name) like lower('?%') or lower(p.description) like lower('%?%'))
			var predicate = cb.or(
					cb.like(cb.lower(root.get(Product_.name)), param.toLowerCase().concat("%")),
					cb.like(cb.lower(root.get(Product_.description)), "%".concat(param.toLowerCase()).concat("%"))
					);
			list.add(predicate);
		});
		
		priceFrom.filter(a -> a > 0).ifPresent(param -> {
			var predicate = cb.ge(root.get(Product_.price), param);
			list.add(predicate);
		});
		
		priceTo.filter(a -> a > 0).ifPresent(param -> {
			var predicate = cb.le(root.get(Product_.price), param);
			list.add(predicate);
		});
		
		status.ifPresent(param -> {
			var predicate = cb.equal(root.get(Product_.status), param);
			list.add(predicate);
		});
		
		return list.toArray(size -> new Predicate[size]);
	}
}
