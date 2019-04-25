package com.cg.dao;

import com.cg.beans.Customer;

public interface AccountCreationDAO {
	public Customer register(Customer cust);
	public Customer login(int accNo,String password);
}
