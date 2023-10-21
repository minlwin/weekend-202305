package com.jdc.onestop.weekend.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.weekend.products.model.entity.ProductHistory;
import com.jdc.onestop.weekend.products.model.output.ProductUploadEvent;
import com.jdc.onestop.weekend.products.model.repo.ProductHistoryRepo;

@Service
public class ProductHistoryEventListener {

	@Autowired
	private ProductHistoryRepo repo;
	
	@EventListener
	@Transactional
	public void handle(ProductUploadEvent event) {
		event.products().stream().map(p -> new ProductHistory(p))
			.forEach(p -> {
				repo.save(p);
			});
	}
}
