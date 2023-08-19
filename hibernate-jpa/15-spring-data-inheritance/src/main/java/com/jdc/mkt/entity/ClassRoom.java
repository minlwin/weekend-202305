
package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClassRoom implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date start_date;
	@Temporal(TemporalType.DATE)
	private Date end_date;
	private int fees;
	
	public ClassRoom(String name, Date start_date, Date end_date, int fees) {
		super();
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.fees = fees;
	}
	
	
	
}
