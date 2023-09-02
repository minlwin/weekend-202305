package com.jdc.balance.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.BalanceDto;
import com.jdc.balance.model.dto.TransactionDetailsDto;
import com.jdc.balance.model.dto.TransactionDto;
import com.jdc.balance.model.form.TransactionForm;

public interface TransactionService {

	TransactionDetailsDto create(TransactionForm form);
	
	TransactionDetailsDto update(long id, TransactionForm form);
	
	Optional<TransactionDetailsDto> findById(long id);
	
	List<TransactionDto> search(
			String username, 
			Optional<LedgerType> type, 
			Optional<String> ledger,
			Optional<LocalDate> from,
			Optional<LocalDate> to);
	
	List<BalanceDto> searchBalance(
			String username, 
			Optional<LocalDate> from,
			Optional<LocalDate> to);
}
