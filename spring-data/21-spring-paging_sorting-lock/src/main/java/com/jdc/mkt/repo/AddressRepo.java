package com.jdc.mkt.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.mkt.entity.Address;

public interface AddressRepo extends JpaRepositoryImplementation<Address, Integer>{

}
