package com.jdc.security.demo.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.security.demo.model.entity.Account;

public interface AccountRepo extends JpaRepositoryImplementation<Account, Integer>{
	
	Optional<Account> findOneByEmail(String email);

}
