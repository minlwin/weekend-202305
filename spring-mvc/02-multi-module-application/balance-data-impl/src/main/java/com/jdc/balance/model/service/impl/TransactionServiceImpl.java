package com.jdc.balance.model.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.BalanceDto;
import com.jdc.balance.model.dto.TransactionDetailsDto;
import com.jdc.balance.model.dto.TransactionDto;
import com.jdc.balance.model.form.TransactionForm;
import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.model.service.TransactionService;
import com.jdc.balance.model.service.helper.TransactionFormHelper;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private AccountService accountService;
	
	private SimpleJdbcInsert insert;
	private SimpleJdbcInsert itemInsert;
	private JdbcTemplate template;
	
	public TransactionServiceImpl(DataSource dataSource) {
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("transaction");
		insert.setGeneratedKeyName("id");
		
		itemInsert = new SimpleJdbcInsert(dataSource);
		itemInsert.setTableName("transaction_item");
		itemInsert.setGeneratedKeyName("id");
		
		template = new JdbcTemplate(dataSource);
	}

	@Override
	@Transactional
	public TransactionDetailsDto create(TransactionForm form) {
		
		// Insert Transaction
		var transactionId = insert.executeAndReturnKey(TransactionFormHelper.insertParams(form, 
				username -> accountService.findByEmail(username)
					.orElseThrow(() -> new IllegalArgumentException("Invalid account."))))
				.longValue();
		
		// Insert Transaction Items with transaction id
		for(var item : form.getItems()) {
			var params = TransactionFormHelper.insertParams(transactionId, item);
			itemInsert.execute(params);
		}
		
		return findById(transactionId).orElseThrow();
	}

	@Override
	@Transactional
	public TransactionDetailsDto update(long id, TransactionForm form) {
		// Delete Items
		template.update("delete from transaction_item where transaction_id = ?", id);
		
		// Update Transaction Table
		template.update("""
			update transaction set ledger_id = ?, issue_at = ?, remark = ?, deleted = ? 
			where id = ?""", 
				stmt -> {
					stmt.setInt(1, form.getLedgerId());
					stmt.setDate(2, Date.valueOf(form.getIssueAt()));
					stmt.setString(3, form.getRemark());
					stmt.setBoolean(4, form.isDeleted());
					stmt.setLong(5, id);
				});
		
		// Insert Transaction Items with transaction id
		for(var item : form.getItems()) {
			var params = TransactionFormHelper.insertParams(id, item);
			itemInsert.execute(params);
		}
		
		return findById(id).orElseThrow();
	}

	@Override
	public Optional<TransactionDetailsDto> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<TransactionDto> search(String username, 
			Optional<LedgerType> type, 
			Optional<String> ledger,
			Optional<LocalDate> from, 
			Optional<LocalDate> to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BalanceDto> searchBalance(String username, Optional<LocalDate> from, Optional<LocalDate> to) {
		// TODO Auto-generated method stub
		return null;
	}

}
