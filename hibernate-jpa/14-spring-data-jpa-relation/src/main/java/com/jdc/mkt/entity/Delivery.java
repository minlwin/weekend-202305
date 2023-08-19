package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

@Entity
@Getter
@Setter
public class Delivery implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String phone;
	@ManyToMany
	@JoinTable(name = "delivery_customer",
	joinColumns = 
	@JoinColumn(name = "delivery_id"),
	inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private List<Customer>customers;
	
}
