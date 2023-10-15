package com.jdc.aop.demo.model;

import com.jdc.aop.demo.entity.Student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentForm(
		@NotBlank(message = "Please enter name.")
		String name,
		@NotBlank(message = "Please enter phone.")
		String phone,
		@NotBlank(message = "Please enter email.")
		@Email(message = "Please enter valid email form.")
		String email
		) {

	public Student entity() {
		var entity = new Student();
		entity.setName(name);
		entity.setPhone(phone);
		entity.setEmail(email);
		return entity;
	}

}
