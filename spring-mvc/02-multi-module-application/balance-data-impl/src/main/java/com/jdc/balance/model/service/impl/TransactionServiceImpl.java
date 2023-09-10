package com.jdc.balance.model.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.BalanceDto;
import com.jdc.balance.model.dto.TransactionDetailsDto;
import com.jdc.balance.model.dto.TransactionDto;
import com.jdc.balance.model.dto.TransactionItemDto;
import com.jdc.balance.model.form.TransactionForm;
import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.model.service.TransactionService;
import com.jdc.balance.model.service.helper.TransactionFormHelper;

@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private AccountService accountService;
	
	private SimpleJdbcInsert insert;
	private SimpleJdbcInsert itemInsert;
	private JdbcTemplate template;
	
	private final String SELECT = """
			select t.id, l.type, l.id ledgerId, l.name ledgerName, t.issue_at issueAt, t.remark, 
			sum(ti.unit_price * ti.quantity) amount from transaction t 
			join ledger l on t.ledger_id = l.id 
			join account a on t.account_id = a.id 
			join transaction_item ti on t.id = ti.transaction_id""";
	
	private final String GROUP_BY = """
			group by t.id, l.type, l.name, t.issue_at, t.remark""";
	
	private final String LAST_BALANCE_SQL = """
			select sum(ti.unit_price * ti.quantity) from from transaction t 
			join ledger l on t.ledger_id = l.id 
			join account a on t.account_id = a.id 
			join transaction_item ti on t.id = ti.transaction_id 
			where a.email = ? and l.type = ? and t.issue_at < ? 
			order by t.id""";
	
	private final RowMapper<TransactionDto> transRowMapper;
	private final RowMapper<TransactionItemDto> itemRowMapper;
	
	public TransactionServiceImpl(DataSource dataSource) {
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("transaction");
		insert.setGeneratedKeyName("id");
		
		itemInsert = new SimpleJdbcInsert(dataSource);
		itemInsert.setTableName("transaction_item");
		itemInsert.setGeneratedKeyName("id");
		
		template = new JdbcTemplate(dataSource);
		
		transRowMapper = new DataClassRowMapper<TransactionDto>(TransactionDto.class);
		itemRowMapper = new DataClassRowMapper<TransactionItemDto>(TransactionItemDto.class);
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
        return findTransactionById(id)
        		.map(transaction -> findDetails(transaction));
	}

	private TransactionDetailsDto findDetails(TransactionDto transaction) {
		var items = template.query(
				"""
				select item, unit_price unitPrice, quantity from 
				transaction_item where transaction_id = ?""", itemRowMapper, transaction.id());
		return new TransactionDetailsDto(transaction, items);
	}

	private Optional<TransactionDto> findTransactionById(long id) {
		return template.queryForStream("%s where t.id = ? %s".formatted(SELECT, GROUP_BY), 
				transRowMapper, id).findAny();
	}

	@Override
	public List<TransactionDto> search(String username, 
			Optional<LedgerType> type, 
			Optional<String> ledger,
			Optional<LocalDate> from, 
			Optional<LocalDate> to) {
		
		var sql = new StringBuffer(SELECT);
		var params = new ArrayList<Object>();
		
		sql.append(" where a.email = ?");
		params.add(username);
		
		type.ifPresent(value -> {
			sql.append(" and l.type = ?");
			params.add(value);
		});
		
		ledger.filter(StringUtils::hasLength).ifPresent(value -> {
			sql.append(" and lower(l.name) like ?");
			params.add("%%%s%%".formatted(value.toLowerCase()));
		});
		
		from.ifPresent(value -> {
			sql.append(" and t.issue_at >= ?");
			params.add(Date.valueOf(value));
		});
		
		to.ifPresent(value -> {
			sql.append(" and t.issue_at <= ?");
			params.add(Date.valueOf(value));
		});
		
		sql.append(" ").append(GROUP_BY);
		sql.append(" order by t.id desc");

		return template.query(sql.toString(), transRowMapper, params.toArray());
	}

	@Override
	public List<BalanceDto> searchBalance(String username, 
			Optional<LocalDate> from, 
			Optional<LocalDate> to) {
		
		var sql = new StringBuffer(SELECT);
		var params = new ArrayList<Object>();
		
		sql.append(" where a.email = ?");
		params.add(username);

		from.ifPresent(value -> {
			sql.append(" and t.issue_at >= ?");
			params.add(Date.valueOf(value));
		});
		
		to.ifPresent(value -> {
			sql.append(" and t.issue_at <= ?");
			params.add(Date.valueOf(value));
		});

		sql.append(" ").append(GROUP_BY);
		sql.append(" order by t.id desc");
		
		var transactions = template.query(sql.toString(), transRowMapper, params.toArray());
		var lastBalance = findLastBlance(username, from);
		
		var result = new ArrayList<BalanceDto>();
		
		for(var trans : transactions) {
			lastBalance = trans.type() == LedgerType.Credit ? 
					lastBalance + trans.amount() : lastBalance - trans.amount();
			result.add(new BalanceDto(trans, lastBalance));
		}

		return result;
	}
	
	private long findLastBlance(String username, Optional<LocalDate> from) {
		
		if(from.isEmpty()) {
			return 0;
		}
		
		var credits = findLastBlance(username, LedgerType.Credit, from.get());
		var debits = findLastBlance(username, LedgerType.Debit, from.get());
		
		return credits - debits;
	}

	private long findLastBlance(String username, LedgerType type, LocalDate from) {
		return template.queryForObject(LAST_BALANCE_SQL, Long.class, username, type, Date.valueOf(from));
	}
}
