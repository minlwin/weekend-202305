package com.jdc.mkt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.mkt.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
