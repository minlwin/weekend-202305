package com.jdc.rest.security.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.rest.security.model.entity.MemberAccess;

public interface MemberAccessRepo extends JpaRepositoryImplementation<MemberAccess, String>{

}
