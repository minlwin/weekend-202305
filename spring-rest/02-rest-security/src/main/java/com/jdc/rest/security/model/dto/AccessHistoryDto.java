package com.jdc.rest.security.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.rest.security.model.entity.AccessHistory;
import com.jdc.rest.security.model.entity.AccessHistory.AccessStatus;

public record AccessHistoryDto(
		String loginId,
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		LocalDateTime accessAt,
		AccessStatus status,
		String remark
		) {

	public static AccessHistoryDto from(AccessHistory entity) {
		return new AccessHistoryDto(
				entity.getId().getMemberId(), 
				entity.getId().getAccessAt(), 
				entity.getStatus(), 
				entity.getRemark());
	}
}
