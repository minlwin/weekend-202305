package com.jdc.balance.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.LedgerDto;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.service.LedgerService;

@Service
public class LedgerServiceImpl implements LedgerService{

	@Override
	public LedgerDto create(LedgerForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LedgerDto update(int id, LedgerForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<LedgerDto> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<LedgerDto> search(String username, Optional<LedgerType> type, Optional<String> name) {
		// TODO Auto-generated method stub
		return null;
	}

}
