package com.jdc.mkt.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String name;
	private int fees;

	public Course(int id, String name, int fees) {
		super();
		this.id = id;
		this.name = name;
		this.fees = fees;
	}

}
