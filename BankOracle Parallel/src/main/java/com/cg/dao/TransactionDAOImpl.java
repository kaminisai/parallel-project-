package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.exception.InvalidIdException;
import com.cg.utility.Database;

public class TransactionDAOImpl implements TransactionDAO {
	Database db = new Database();
	Connection con = db.getConnection();

	public int deposit(int accNo, int balance, int amount) {
		balance += amount;
		int i = updateBalance(balance, accNo);
		if (i == 1)
			return balance;
		else
			return 0;
	}

	private int updateBalance(int amount, int accNo) {
	
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update customer_details set balance = ? where account_no = ?");
			ps.setInt(1, amount);
			ps.setInt(2, accNo);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int withdraw(int accNo, int balance, int amount) {
		if (balance > amount)
			balance -= amount;
		int i = updateBalance(balance, accNo);
		if (i == 1)
			return balance;
		else
			return 0;
	}

	public int balance(int accNo) {
		int bal = 0;
		try {
			PreparedStatement ps = con.prepareStatement("select * from customer_details where account_no=?");
			ps.setInt(1, accNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bal = rs.getInt(10);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bal;
	}

	public int transfer(int fromAccountNo, int toAccountNo, int amount) {
		int c = 0, toBal = 0, fromBal = 0, i = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer_details");
			while (rs.next()) {
				if (rs.getInt(1) == toAccountNo) {
					c++;
					toBal = rs.getInt(10);
				}
				if (rs.getInt(1) == fromAccountNo) {
					fromBal = rs.getInt(10);
				}
			}
			if (c == 1 && fromBal > amount) {
				i = insertAmt(fromAccountNo, toAccountNo, amount);
				if (i == 1) {
					toBal += amount;
					fromBal -= amount;
					updateBalance(fromBal, fromAccountNo);
					updateBalance(toBal, toAccountNo);
				}

			} else {
				try {
					throw new InvalidIdException();
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fromBal;
	}

	private int insertAmt(int fromAccountNo, int toAccountNo, int amount) {
		// TODO Auto-generated method stub
		int i = 0;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into transaction_details(transaction_no,fromAccountNo,toAccountNo,amount) values(transaction_no.nextval,?,?,?)");
			ps.setInt(1, fromAccountNo);
			ps.setInt(2, toAccountNo);
			ps.setInt(3, amount);

			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}
}
