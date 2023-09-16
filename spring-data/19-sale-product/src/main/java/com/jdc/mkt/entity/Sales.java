package com.jdc.mkt.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sales")
public class Sales implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Customer customer;
	@Column(nullable = false,name = "sale_date")
	private LocalDate saleDate;
	@OneToMany(mappedBy = "sales",cascade = CascadeType.PERSIST)
	private List< SaleDetails> saleDetials = new ArrayList<SaleDetails>();
	
	
	public Sales(Customer customer ,LocalDate saleDate) {
		super();
		this.saleDate = saleDate;
		this.customer = customer;
		
	}
	
	public void addSaleDetail(SaleDetails detail) {
		detail.setSales(this);
		this.saleDetials.add(detail);
	}
	
}
