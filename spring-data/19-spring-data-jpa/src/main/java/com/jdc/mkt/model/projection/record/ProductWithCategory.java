package com.jdc.mkt.model.projection.record;

import com.jdc.mkt.entity.Category;

public record ProductWithCategory(
		String name,
		Category category		
		) {

	public String showDisplay() {
		return name+"\t"+category.getName();
	}
}
