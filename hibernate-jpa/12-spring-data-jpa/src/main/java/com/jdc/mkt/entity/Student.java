package com.jdc.mkt.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter
@DiscriminatorValue("B")
public class Student extends Account{

	private static final long serialVersionUID = 1L;
	
	

	public Student() {
		setRole(Role.STUDENT);
	}
	
	
	public Student(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.TEACHER);
	}

}
