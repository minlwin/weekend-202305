package com.jdc.rest.security.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.rest.security.model.dto.AccessHistoryDto;
import com.jdc.rest.security.model.entity.AccessHistory;
import com.jdc.rest.security.model.repo.AccessHistoryRepo;

@Service
public class AccessHistoryService {
	
	@Autowired
	private AccessHistoryRepo repo;
	
	@PreAuthorize("hasAuthority('Admin') OR (hasAuthority('Member') AND authentication.name eq #username)")
	public List<AccessHistoryDto> getAccessHistory(
			String username, Optional<LocalDate> from, Optional<LocalDate> to) {
		return repo.findAll(whichUser(username).and(whichFrom(from)).and(whichTo(to))).stream()
				.map(AccessHistoryDto::from).toList();
	}
	
	private Specification<AccessHistory> whichUser(String param) {
		return !StringUtils.hasLength(param) ? Specification.where(null) : 
			(root, query, cb) -> cb.like(cb.lower(root.get("id").get("memberId")), param.toLowerCase().concat("%"));
	}

	private Specification<AccessHistory> whichFrom(Optional<LocalDate> param) {
		return null == param || param.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.greaterThanOrEqualTo(root.get("id").get("accessAt"), param.get().atStartOfDay());
	}

	private Specification<AccessHistory> whichTo(Optional<LocalDate> param) {
		return null == param || param.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.lessThan(root.get("id").get("accessAt"), param.get().plusDays(1).atStartOfDay());
	}
}
