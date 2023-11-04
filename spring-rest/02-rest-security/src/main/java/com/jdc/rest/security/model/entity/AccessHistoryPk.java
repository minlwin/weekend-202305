package com.jdc.rest.security.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AccessHistoryPk implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "access_at")
	private LocalDateTime accessAt;
}
