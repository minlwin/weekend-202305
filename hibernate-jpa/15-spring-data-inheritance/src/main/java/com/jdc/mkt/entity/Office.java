package com.jdc.mkt.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("O")
public class Office extends Account{

	private static final long serialVersionUID = 1L;	
	private boolean isAdmin;
	
	public Office(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.OFFICE);
	}
	
	

}
