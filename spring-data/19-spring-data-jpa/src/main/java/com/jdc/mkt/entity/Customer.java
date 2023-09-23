package com.jdc.mkt.entity;

import java.io.Serializable;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(
		name = "selectByNameLiked",
		query = """
				select c.name name,c.phone phone,c.email email from Customer c 
				where lower(c.name) like lower(:name)
				""")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(nullable = false,unique = true)
	private String phone;
	@Column(columnDefinition = "varchar(40) not null check(email like '%@%')")
	private String email;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Address address;
	
	
	public void addAddress(Address address) {
		address.setMember(this);
		this.address = address;
	}
	
	public Customer(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public enum Role{
		ADMIN,CUSTOMER
	}
}
