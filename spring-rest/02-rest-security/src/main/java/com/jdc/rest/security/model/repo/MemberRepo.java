package com.jdc.rest.security.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.rest.security.model.entity.Member;

public interface MemberRepo extends JpaRepositoryImplementation<Member, String>{

}
