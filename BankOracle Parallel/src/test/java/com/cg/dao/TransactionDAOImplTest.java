package com.cg.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.beans.Customer;

class TransactionDAOImplTest {

	static TransactionDAOImpl td;
	static Customer cust;
	
	@BeforeAll
	
	public static void init() {
		td=new TransactionDAOImpl();
		cust=new Customer();
	}
	@Test
	void testDeposit() {
		cust.setBalance(100);
		cust.setAccountNo(1111100001);
		
		int cust1=td.deposit(200,1111100001, 100);
		
		assertEquals(0,td.deposit(100, 1111100001, 100));
		}

	@Test
	void testWithdraw() {
		cust.setBalance(1200);
		cust.setAccountNo(1111100002);
		
		int cust1=td.deposit(1200,1111100001, 120);
		
		assertEquals(0,td.withdraw(100, 1111100001, 120));
		
	}

	@Test
	void testBalance() {
		
		assertEquals(5010,td.balance(1111100001));
	}

	@Test
	void testTransfer() {
		assertEquals(5005,td.transfer(1111100001, 1111100002,5));
	}

}
