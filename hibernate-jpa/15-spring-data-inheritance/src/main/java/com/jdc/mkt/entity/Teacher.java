package com.jdc.mkt.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("T")
public class Teacher extends Account{

	private static final long serialVersionUID = 1L;
	@ManyToMany
	private List<Course> courses;
	
	public Teacher(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.TEACHER);
	}
	
	
}
