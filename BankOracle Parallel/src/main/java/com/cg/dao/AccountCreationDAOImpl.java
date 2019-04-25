package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.beans.Customer;
import com.cg.utility.Database;

public class AccountCreationDAOImpl implements AccountCreationDAO {

	Database db = new Database();
	Connection con = db.getConnection();

	public Customer register(Customer cust) {
		int c = 0, i = 0;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from customer_details");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (cust.getAadharNo().equals(rs.getString(7))) {
					c++;
				}
			}  
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (c == 0) {
			try {
				ps = con.prepareStatement(
				"insert into customer_details(account_no,first_name,last_name,email_id,pancard_no,password,aadhar_no,address,mobile_no,balance) values(account_no.nextval,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, cust.getFirstName());
				ps.setString(2, cust.getLastName());
				ps.setString(3, cust.getEmailId());
				ps.setLong(4, cust.getPancardNo());
				ps.setString(5, cust.getPassword());
				ps.setString(6, cust.getAadharNo());
				ps.setString(7, cust.getAddress());
				ps.setString(8, cust.getMobileNo());
				ps.setInt(9, cust.getBalance());
				i = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (i == 1) {
			try {
				ps = con.prepareStatement("select * from customer_details where aadhar_no = ?");
				ps.setString(1, cust.getAadharNo());
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					cust.setAccountNo(rs1.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cust;
	}

	public Customer login(int accNo, String password) {
		Customer cust = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer_details");
			while (rs.next()) {
				if (rs.getInt(1) == accNo && rs.getString(6).equals(password)) {
					cust = new Customer();
					cust.setAccountNo(accNo);
					cust.setFirstName(rs.getString(2));
					cust.setLastName(rs.getString(3));
					cust.setEmailId(rs.getString(4));
					cust.setPancardNo(rs.getInt(5));
					cust.setAadharNo(rs.getString(7));
					cust.setAddress(rs.getString(8));
					cust.setMobileNo(rs.getString(9));
					cust.setBalance(rs.getInt(10));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}
}