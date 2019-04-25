package com.cg.service;

import com.cg.beans.Customer;

public interface AccountCreationService {
	public Customer register(Customer cust);
	public Customer login(int accNo,String password);
}
