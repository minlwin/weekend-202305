package com.jdc.weekend.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.weekend.model.entity.TransactionItem;

public interface TransactionItemRepo extends JpaRepositoryImplementation<TransactionItem, Integer>{

}
