package com.jdc.weekend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.TransactionForm;
import com.jdc.weekend.model.entity.Transaction;
import com.jdc.weekend.model.repo.TransactionRepo;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class TransactionService {
	
	@Autowired
	private TransactionRepo repo;

	public List<Transaction> findAll() {
		return repo.findAll();
	}

	public Transaction findById(int id) {
		return repo.findById(id).orElseThrow();
	}

	@Transactional
	public int save(TransactionForm form) {
		return repo.save(form.entity()).getId();
	}

}
