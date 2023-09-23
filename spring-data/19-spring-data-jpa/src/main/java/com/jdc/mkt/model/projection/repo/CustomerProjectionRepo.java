package com.jdc.mkt.model.projection.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jdc.mkt.entity.Customer;
import com.jdc.mkt.model.projection.Inf.CustomerProjectionInf;

public interface CustomerProjectionRepo extends JpaRepository<Customer, Integer>{

	@Query(name="selectByNameLiked")
	List<CustomerProjectionInf> getCustomerByNameLike(@Param("name") String name);
}
