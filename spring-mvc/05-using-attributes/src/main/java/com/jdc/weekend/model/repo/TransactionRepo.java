package com.jdc.weekend.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.weekend.model.entity.Transaction;

public interface TransactionRepo extends JpaRepositoryImplementation<Transaction, Integer>{

}
