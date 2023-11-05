package com.jdc.rest.security.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.rest.security.model.entity.AccessHistory;
import com.jdc.rest.security.model.entity.AccessHistoryPk;

public interface AccessHistoryRepo extends JpaRepositoryImplementation<AccessHistory, AccessHistoryPk>{

	long countByIdMemberId(String memberId);
}
