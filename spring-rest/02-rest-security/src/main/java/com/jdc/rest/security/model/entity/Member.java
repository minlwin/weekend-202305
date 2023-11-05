package com.jdc.rest.security.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Member {
	
	public enum Role {
		Admin, Member
	}

	@Id
	private String loginId;
	private String name;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Role role;
	private String email;
	private LocalDateTime registAt;
	
	@OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private MemberAccess access;
}
