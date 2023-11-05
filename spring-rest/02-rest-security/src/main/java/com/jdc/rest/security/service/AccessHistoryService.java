package com.jdc.rest.security.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.rest.security.model.dto.AccessHistoryDto;

@Service
public class AccessHistoryService {

	public List<AccessHistoryDto> getAccessHistory(Optional<String> username, Optional<LocalDate> from, Optional<LocalDate> to) {
		// TODO Auto-generated method stub
		return null;
	}
}
