package com.jdc.balance.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.balance.model.constants.LedgerType;
import com.jdc.balance.model.dto.AccountDto;
import com.jdc.balance.model.dto.LedgerDto;
import com.jdc.balance.model.form.LedgerForm;
import com.jdc.balance.model.service.AccountService;
import com.jdc.balance.model.service.LedgerService;
import com.jdc.balance.model.service.helper.LedgerFormHelper;

@Service
public class LedgerServiceImpl implements LedgerService{
	
	@Autowired
	private AccountService accountService;
	
	private JdbcTemplate template;
	private SimpleJdbcInsert insert;
	
	private final RowMapper<LedgerDto> rowMapper = 
			(rs, rowNum) -> new LedgerDto(rs.getInt("id"), 
					rs.getString("name"), 
					LedgerType.valueOf(rs.getString("type")), 
					rs.getLong("tx_count"), 
					rs.getLong("tx_amount"));
			
	private static final String SELECT = """
			select l.id, l.type, l.name, count(t.id) tx_count, sum(ti.qantity * ti.unit_price) tx_amount 
			from ledger l join transaction t on t.ledger_id = l.id 
			join transaction_item ti on ti.transaction_id = t.id""";
	
	private static final String GROUP_BY = " group by l.id, l.type, l.name";
			
	public LedgerServiceImpl(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("ledger");
		insert.setGeneratedKeyName("id");
	}		

	@Override
	@Transactional
	public LedgerDto create(LedgerForm form) {
		var id = insert.executeAndReturnKey(LedgerFormHelper.insertParams(form, 
				username -> accountService.findByEmail(username)
					.orElseThrow(() -> new IllegalArgumentException("There is no account to create ledger."))))
				.intValue();
		return findById(id).orElseThrow();
	}

	@Override
	@Transactional
	public LedgerDto update(int id, LedgerForm form) {
		
		var sql = "update ledger name = ?, type = ?, deleted = ? where id = ?";
		
		template.update(sql, stmt -> {
			stmt.setString(1, form.getName());
			stmt.setString(2, form.getType().name());
			stmt.setBoolean(3, form.isDeleted());
			stmt.setInt(4, id);
		});
		
		return findById(id).orElseThrow();
	}

	@Override
	public Optional<LedgerDto> findById(int id) {
		var sql = "%s where l.id = ? %s".formatted(SELECT, GROUP_BY);
		return template.queryForStream(sql, rowMapper, id).findAny();
	}

	@Override
	public List<LedgerDto> search(String username, Optional<LedgerType> type, Optional<String> name) {
		
		var sql = new StringBuffer(SELECT);
		var params = new ArrayList<Object>();

		AccountDto account = accountService.findByEmail(username)
				.orElseThrow(() -> new IllegalArgumentException("Invalid User"));
		sql.append(" where l.account_id = ?");
		params.add(account.id());
		
		if(type.isPresent()) {
			sql.append(" and l.type = ?");
			params.add(type.get().name());
		}
		
		if(name.filter(StringUtils::hasLength).isPresent()) {
			sql.append(" and lower(l.name) like ?");
			params.add(name.get().toLowerCase().concat("%"));
		}
		
		sql.append(GROUP_BY);
		
		return template.query(sql.toString(), rowMapper, params.toArray());
	}

}
