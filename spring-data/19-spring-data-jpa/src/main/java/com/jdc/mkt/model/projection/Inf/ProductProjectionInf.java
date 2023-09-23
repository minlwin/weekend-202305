package com.jdc.mkt.model.projection.Inf;

import org.springframework.beans.factory.annotation.Value;

public interface ProductProjectionInf {

	int getId();
	String getName();
	int getDetailPrice();
	CategoryProjectionInf getCategory();
	
	@Value("#{target.category.id+' '+target.category.name}")
	String showCategory();
	
	public default String showDisplay() {
		return "Id :%d\tName :%.5s Price :%d"
				.formatted(getId(),getName(),getDetailPrice());
	}
}
