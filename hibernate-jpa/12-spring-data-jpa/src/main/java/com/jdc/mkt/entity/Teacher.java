package com.jdc.mkt.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("A")
public class Teacher  extends Account{

	private static final long serialVersionUID = 1L;
	
	
	
	public Teacher() {
		
	}



	public Teacher(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.TEACHER);
	}



	

}
