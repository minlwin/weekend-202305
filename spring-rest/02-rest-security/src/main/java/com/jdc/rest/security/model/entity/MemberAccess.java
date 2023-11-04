package com.jdc.rest.security.model.entity;

import java.time.LocalDateTime;

import com.jdc.rest.security.model.entity.AccessHistory.AccessStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class MemberAccess {

	@Id
	private String memberId;
	@OneToOne
	@MapsId
	private Member member;
	private LocalDateTime firstLoginAt;
	private LocalDateTime lastLoginAt;
	private AccessStatus lastLoginStatus;
}
