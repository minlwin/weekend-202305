package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 40)
	private String name;
	@Enumerated(EnumType.STRING)
	private CustomerType type;
	
	//@MapsId
	//@PrimaryKeyJoinColumn
	@OneToOne
//	@JoinTable(name = "customer_address_tbl",
//	joinColumns = @JoinColumn(name = "customer_id"),
//	inverseJoinColumns =@JoinColumn(name = "address_id") )
	private Address address;
	@ManyToMany(mappedBy = "customers")
	private List<Delivery> deliveries;
	
	

	public Customer(String name, CustomerType type, Address address) {
		super();
		//this.id = address.getId();
		this.name = name;
		this.type = type;
		this.address = address;
	}

	public enum CustomerType{
		GOLD,SILVER,PLATINUM
	}
}











