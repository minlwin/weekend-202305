package com.jdc.mkt.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("S")
public class Student extends Account{

	private static final long serialVersionUID = 1L;
	@OneToMany
	private List<ClassRoom> rooms;
	
	
	public Student(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.STUDENT);
	}
	
	
}
