package com.jdc.mkt.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter
@DiscriminatorValue("C")
public class Office extends Account{

	private static final long serialVersionUID = 1L;
	
	private boolean isAdmin;
	
	public Office() {
		setRole(Role.OFFICE);
	}

	public Office(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.TEACHER);
	}
}
