package com.jdc.mkt.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jdc.mkt.listener.EnableTimeListener;
import com.jdc.mkt.listener.EnableTimes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="product")
@EntityListeners(EnableTimeListener.class)
public class Product  implements EnableTimes{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;
	
	private Times times;
	
	@ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private Category category;
	
	
	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
		
	}


	


	
	
	
//	@PrePersist
//	void beforePersist() {
//		System.out.println("Before persist");
//	}
//	@PostPersist
//	void afterPersist() {
//		System.out.println("After persist");
//	}
//	
//	@PostLoad
//	void afterFind() {
//		System.out.println("After find");
//	}
//	
//	@PreRemove
//	void beforeRemove() {
//		System.out.println("Before remove");
//	}
//	
//	@PreUpdate
//	void beforeUpdate() {
//		System.out.println("Before update");
//	}
	
}
