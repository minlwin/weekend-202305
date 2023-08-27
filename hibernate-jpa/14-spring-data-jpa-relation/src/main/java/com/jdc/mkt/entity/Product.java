package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 40)
	private String name;
	
	@MapKeyEnumerated(EnumType.STRING)
	@ElementCollection
	private Map<Price, Integer> price;
	@ManyToOne(cascade = { PERSIST, MERGE })
	private Category category;
	
	public enum Price{
		RETAIL,WHOLESALE;	
	}
}
