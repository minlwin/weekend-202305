package com.jdc.balance.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.LedgerDto;
import com.jdc.balance.model.form.LedgerForm;

public interface LedgerService {

	LedgerDto create(LedgerForm form);
	
	LedgerDto update(int id, LedgerForm form);
	
	Optional<LedgerDto> findById(int id);
	
	List<LedgerDto> search(String username, Optional<LedgerType> type, Optional<String> name);
}
