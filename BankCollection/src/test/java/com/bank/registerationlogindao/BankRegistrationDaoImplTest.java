package com.bank.registerationlogindao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import com.bank.model.Account;
import com.bank.transactiondao.BankTransactionDaoImpl;

import junit.framework.Assert;

class BankRegistrationDaoImplTest {

	static Account acc;
	static BankRegistrationDaoImpl bank;
	static BankTransactionDaoImpl dao;
	
	@BeforeAll
	public static void init() {
		bank=new BankRegistrationDaoImpl();
		acc=new Account();
		dao=new BankTransactionDaoImpl();
	}
	@Test
	void testRegistration() {
		acc.setAccountNo(4308741538777404828L);
		acc.setAadharNo(123456789012L);
		acc.setAddress("hyd");
		acc.setEmailId("kam@gmail.com");
		acc.setFirstName("kamini");
		acc.setLastName("sai");
		acc.setMobileNo(9652344161L);
		acc.setPancardNo("123");
		acc.setPassword("12345");
		
	//	assertEquals("hyd",acc.getAddress());
	//	assertEquals("123",acc.getPancardNo());
		assertEquals(4308741538777404828L,bank.registration(acc));
		
	}

	@Test
	void testLogin() {
		acc.setAccountNo(4308741538777404828L);
		acc.setPassword("123");
		assertEquals(4308741538777404828L,acc.getAccountNo());
		assertEquals("123",acc.getPassword());
		
		}

	@Test
	void testValidate() {
		acc.setAadharNo(123456789012L);
		 assertEquals(false, bank.validate(123456789012L));
			
	
	}

	@Test
	void testFundTransfer() {
		 assertEquals(0, bank.fundTransfer(acc, 300, 10000060, 100));
	}
	
	@Test
	void testWithdraw() {
		assertEquals(1,dao.withdraw(acc, 10));	
	}

	@Test
	void testDeposit()
	{
	assertEquals(1,dao.deposit(acc, 10));	
  }


}
