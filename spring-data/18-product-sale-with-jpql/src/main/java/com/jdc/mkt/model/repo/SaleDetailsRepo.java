package com.jdc.mkt.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.mkt.entity.SaleDetails;
import com.jdc.mkt.entity.SaleDetailsPk;

public interface SaleDetailsRepo extends JpaRepository<SaleDetails, SaleDetailsPk> {

}
