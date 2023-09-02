package com.jdc.balance.model.service;

import java.util.List;
import java.util.Optional;

import com.jdc.balance.model.constants.Role;
import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.form.AccountForm;

public interface AccountService {

	AccountDto create(AccountForm form);
	
	AccountDto update(int id, AccountForm form);

	Optional<AccountDto> findByEmail(String email);
	
	List<AccountDto> search(Optional<Role> role, Optional<String> name, Optional<Boolean> deleted);
}
