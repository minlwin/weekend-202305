package com.jdc.aop.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Please enter name")
	private String name;
	@NotBlank(message = "Please enter email")
	private String email;
	@NotBlank(message = "Please enter phone")
	private String phone;
}
