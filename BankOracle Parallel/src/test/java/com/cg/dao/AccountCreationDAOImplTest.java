package com.cg.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.beans.Customer;

class AccountCreationDAOImplTest {

	static AccountCreationDAOImpl acc;
	static Customer cust;
	
	@BeforeAll
	
	public static void init() {
		acc=new AccountCreationDAOImpl();
		cust=new Customer();
	}
	
	@Test
	void testRegister() {
		//fail("Not yet implemented");
		
		cust.setFirstName("kamini");
		cust.setLastName("Sai");
		cust.setEmailId("kam@gmail.com");
		cust.setPassword("12345");
		cust.setPancardNo((int) 1234345L);
		cust.setAadharNo("234567890123");
		cust.setAddress("hyd");
		cust.setPassword("12456");
		cust.setMobileNo("9652344161");
		cust.setBalance(0);
		cust=acc.register(cust);
		assertEquals("kamini",cust.getFirstName());
		assertEquals("kam@gmail.com",cust.getEmailId());
		assertEquals("12456",cust.getPassword());
		
	}

	@Test
	void testLogin() {
		
		cust=acc.login(1111100000, "12456");
		assertEquals(1111100000,cust.getAccountNo());

	}

}
