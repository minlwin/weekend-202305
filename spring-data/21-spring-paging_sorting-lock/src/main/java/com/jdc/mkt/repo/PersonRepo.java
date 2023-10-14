package com.jdc.mkt.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.jdc.mkt.entity.Person;

import jakarta.persistence.LockModeType;

public interface PersonRepo extends JpaRepositoryImplementation<Person, Integer>{

	@Query("select p from Person p where p.address.township = :township order by p.name asc")
	List<Person> findByAddress(@Param("township") String township);	
	List<Person> findByAddressTownshipOrderByNameAsc(String township);
	
	@Query(value = "select p from Person p where p.address.township = :township")
	List<Person> findByAddress(@Param("township")String township,Sort sort);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query(value = "select p from Person p where p.address.township = :township",
			countQuery = "select count(p) from Person p where p.address.township = :township")
	Page<Person> findByAddressWithPage(@Param("township")String township,Pageable page);
}
