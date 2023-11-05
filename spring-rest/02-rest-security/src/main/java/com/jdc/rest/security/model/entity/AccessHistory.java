package com.jdc.rest.security.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class AccessHistory {

	@EmbeddedId
	private AccessHistoryPk id;
	
	private AccessStatus status;
	private String remark;
	
	public enum AccessStatus {
		Success, Error
	}
	
}
