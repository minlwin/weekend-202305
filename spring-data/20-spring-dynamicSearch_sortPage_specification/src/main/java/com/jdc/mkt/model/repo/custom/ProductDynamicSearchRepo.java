package com.jdc.mkt.model.repo.custom;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.jdc.mkt.entity.Product;

@NoRepositoryBean
public interface ProductDynamicSearchRepo {

	List<Product> search(int id,String pname,String cname,int price);
}
