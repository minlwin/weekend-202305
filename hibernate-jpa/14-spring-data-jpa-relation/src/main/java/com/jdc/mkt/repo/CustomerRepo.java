package com.jdc.mkt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.mkt.entity.Customer;


public interface CustomerRepo extends JpaRepository<Customer, Integer>{

//	@PersistenceContext
//	private EntityManager em;
//	
//	@Transactional
//	public void create(Customer cu) {
//		em.persist(cu.getAddress());
//		em.persist(cu);
//	}
}
