package com.cg.ui;

import java.util.Scanner;
import com.cg.beans.Customer;
import com.cg.service.AccountCreationService;
import com.cg.service.AccountCreationServiceImpl;
import com.cg.service.TransactionService;
import com.cg.service.TransactionServiceImpl;

public class Client {
	static Scanner sc = new Scanner(System.in);
	static Customer cust = new Customer();
	static AccountCreationService account = new AccountCreationServiceImpl();
	static TransactionService transaction = new TransactionServiceImpl();

	public static void main(String[] args) {
		while (true) {
		System.out.println("1) Register \n 2) Login");
	
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("enter first name");
				cust.setFirstName(sc.next());
				System.out.println("enter last name");
				cust.setLastName(sc.next());
				System.out.println("enter email id");
				cust.setEmailId(sc.next());
				System.out.println("enter pancard number");
				cust.setPancardNo(sc.nextInt());
				System.out.println("enter password");
				cust.setPassword(sc.next());
				System.out.println("enter aadhar number");
				cust.setAadharNo(sc.next());
				System.out.println("enter address");
				cust.setAddress(sc.next());
				System.out.println("enter mobile number");
				cust.setMobileNo(sc.next());
				Customer customer = account.register(cust);
				System.out.println("your account number is: " + customer.getAccountNo());
				break;
				
			case 2:
				System.out.println("enter account number");
				int accNo = sc.nextInt();
				System.out.println("enter password");
				String pass = sc.next();
				Customer cust1 = account.login(accNo, pass);
				// System.out.println("hi"+cust1.getAadharNo());
				System.out.println("Enter choice 1) deposit \n 2)Withdraw \n 3)Transaction \n 4)Balance");
			
				options(cust1);
			}
				
		}
	}

	private static void options(Customer cust) {
		// Transaction transaction=new Transaction();
		while (true) {
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("enter amount to be deposited");
				int amount = sc.nextInt();
				int value = transaction.deposit(cust.getAccountNo(), cust.getBalance(), amount);
				if (value > 0) {
					cust.setBalance(value);
					System.out.println(value);
				}

				break;
			case 2:
				System.out.println("enter amount to be withdrawn");
				amount = sc.nextInt();
				value = transaction.withdraw(cust.getAccountNo(), cust.getBalance(), amount);
				if (value > 0) {
					cust.setBalance(value);
					System.out.println(value);
				}
				break;
			case 3:
				System.out.println("enter amount to be transferred");
				amount = sc.nextInt();
				System.out.println("Enter the account no to which the money is to be transferred");
				int accNo = sc.nextInt();
				value = transaction.transfer(cust.getAccountNo(), accNo, amount);
				if (value > 0) {
					cust.setBalance(value);
					System.out.println(amount + "is transferred from" + cust.getAccountNo() + "to" + accNo);
				}
				break;
			case 4:
				System.out.println("balance is:" + transaction.balance(cust.getAccountNo()));
			case 5:
				System.out.println("Thank You");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter correct number between 1-5");
				break;
			}
		}
	}

}