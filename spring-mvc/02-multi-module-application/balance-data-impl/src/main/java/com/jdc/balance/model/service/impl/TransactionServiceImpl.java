package com.jdc.balance.model.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.BalanceDto;
import com.jdc.balance.model.dto.TransactionDetailsDto;
import com.jdc.balance.model.dto.TransactionDto;
import com.jdc.balance.model.form.TransactionForm;
import com.jdc.balance.model.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Override
	public TransactionDetailsDto create(TransactionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionDetailsDto update(long id, TransactionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TransactionDetailsDto> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<TransactionDto> search(String username, Optional<LedgerType> type, Optional<String> ledger,
			Optional<LocalDate> from, Optional<LocalDate> to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BalanceDto> searchBalance(String username, Optional<LocalDate> from, Optional<LocalDate> to) {
		// TODO Auto-generated method stub
		return null;
	}

}
