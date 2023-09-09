package com.jdc.mkt.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String street;
	private String township;
	private String city;
	@OneToOne(mappedBy = "address")
	private Customer member;
	
	
	public Address(String street, String township, String city) {
		super();
		this.street = street;
		this.township = township;
		this.city = city;
	}
	
	
	
}
