package com.jdc.mkt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.model.repo.ProductWithBaseRepo;

@Service
public class ProductWithBase {

	@Autowired
	ProductWithBaseRepo repo;
}
