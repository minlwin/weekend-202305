package com.jdc.onestop.weekend.products.model.output;

import java.util.List;

import com.jdc.onestop.weekend.products.model.entity.Product;

public record ProductUploadEvent(
		List<Product> products
		) {

}
