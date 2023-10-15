package com.jdc.aop.demo.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.aop.demo.entity.Student;

public interface StudentRepo extends JpaRepositoryImplementation<Student, Integer>{

}
