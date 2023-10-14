package com.jdc.mkt;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.jdc.mkt.entity.Address;
import com.jdc.mkt.entity.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
public class AddressLockTest {

	@PersistenceContext
	EntityManager em;
	
	@Test
	@Transactional
	@Rollback(false)
	void test() {
		var address = em.find(Address.class, 1, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
		//em.refresh(Address.class, LockModeType.OPTIMISTIC);
		//em.lock(Address.class, LockModeType.OPTIMISTIC);
		
		address.setTownship(LocalDateTime.now().toString());
	}
}
