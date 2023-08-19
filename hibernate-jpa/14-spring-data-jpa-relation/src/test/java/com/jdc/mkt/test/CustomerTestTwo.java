package com.jdc.mkt.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;
import com.jdc.mkt.entity.Address;
import com.jdc.mkt.entity.Customer;
import com.jdc.mkt.entity.Customer.CustomerType;
import com.jdc.mkt.repo.AddressRepo;
import com.jdc.mkt.repo.CustomerRepo;

@SpringJUnitConfig(classes = ApplicationConfig.class)
public class CustomerTestTwo {

	@Autowired
	private CustomerRepo cuRepo;
	@Autowired
	private AddressRepo adRepo;
	
	@ParameterizedTest
	@CsvSource(value = 
	{	"Andrew:Gold:22st:lathar:yangon",
		"William:Silver:19st:ManharMyaing:mandalay"
	},delimiter = ':')
	
	void test1(String name,String type,String street,String township,String city) {
		var address = new Address(street, township, city);
		adRepo.save(address);
		var customer = new Customer(name, CustomerType.valueOf(type.toUpperCase()),address);
		
		cuRepo.save(customer);
	}
}
