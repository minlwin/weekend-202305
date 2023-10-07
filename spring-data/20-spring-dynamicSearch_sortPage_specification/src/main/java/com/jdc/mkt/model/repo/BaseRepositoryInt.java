package com.jdc.mkt.model.repo;

import java.util.List;

import com.jdc.mkt.entity.Product;


public interface BaseRepositoryInt {

	List<Product> search(int id,String pname,String cname,int price);
}
