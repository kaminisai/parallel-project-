package com.cg.dao;

public interface TransactionDAO{
	public int deposit(int accNo, int balance, int amount);
	public int withdraw(int accNo, int balance, int amount);
	public int balance(int accNo);
	public int transfer(int fromAccountNo,int toAccountNo,int amount);
}
