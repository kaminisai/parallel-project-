package com.cg.service;

import com.cg.beans.Customer;
import com.cg.dao.AccountCreationDAO;
import com.cg.dao.AccountCreationDAOImpl;

public class AccountCreationServiceImpl implements AccountCreationService {
	
	boolean isValidAadharNo(String aadharNo) {
		if(aadharNo.length()!=12)
		return false;
		else
		return true;
	}
	boolean isValidMobileNo(String mobileNo) {
		if(mobileNo.length()!=10)
		return false;
		else
		return true;
	}
	
	public Customer register(Customer cust) {
		AccountCreationDAO accountdao=new AccountCreationDAOImpl();
		Customer customer=accountdao.register(cust);
		return customer;
	}

	public Customer login(int accNo, String password) {
		AccountCreationDAO accountdao=new AccountCreationDAOImpl();
		return accountdao.login(accNo,password);
	}

}
