package com.jdc.rest.security.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Member {

	@Id
	private String loginId;
	private String name;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String role;
	private String email;
	private LocalDateTime registAt;
	
	@OneToOne(mappedBy = "member")
	private MemberAccess access;
}
