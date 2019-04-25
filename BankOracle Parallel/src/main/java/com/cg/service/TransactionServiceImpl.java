package com.cg.service;

import com.cg.dao.TransactionDAO;
import com.cg.dao.TransactionDAOImpl;

public class TransactionServiceImpl implements TransactionService {
	TransactionDAO transactiondao=new TransactionDAOImpl();

	public int deposit(int accNo, int balance, int amount) {
		return transactiondao.deposit(accNo,balance,amount);
	}

	public int withdraw(int accNo, int balance, int amount) {
		return transactiondao.withdraw(accNo,balance,amount);
	}

	public int balance(int accNo) {
		return transactiondao.balance(accNo);
	}

	public int transfer(int fromAccountNo, int toAccountNo,int amount) {
		return transactiondao.transfer(fromAccountNo,toAccountNo,amount);
	}

}
