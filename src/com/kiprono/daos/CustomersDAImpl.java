package com.kiprono.daos;

import com.kiprono.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kiprono.models.Customers;

public class CustomersDAImpl implements CustomersDataAccess {
	private Connection connection;
	private PreparedStatement stmt;
	private ResultSet rs;

	@Override // works
	public Customers getCustomer(int userId) {
		Customers customer = new Customers();
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM customers WHERE userid = ?";

		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();

			rs.next();
			customer.setUserId(rs.getInt("userid"));
			customer.setFirstName(rs.getString("firstname"));
			customer.setLastName(rs.getString("lastname"));
			customer.setMiddleInitial(rs.getString("middleinitial"));
			customer.setAddress(rs.getString("address"));
			customer.setCity(rs.getString("city"));
			customer.setState(rs.getString("state"));
			customer.setZipCode(rs.getInt("zipcode"));
			customer.setPhoneNumber(rs.getString("phonenumber"));
			customer.setUserName(rs.getString("username"));
			customer.setPasswd(rs.getString("passwd"));
			customer.setAdmin(rs.getBoolean("superuser"));
			customer.setAccountNumber(rs.getInt("accountnumber"));
			customer.setKey(rs.getString("secret_key"));
			customer.setVerified(rs.getBoolean("is_approved"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customer;
	}

	@Override // works
	public ArrayList<Customers> getAllCustomers() {
		ArrayList<Customers> customers = new ArrayList<Customers>();
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM customers";

		try {
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Customers customer = new Customers();
				customer.setUserId(rs.getInt("userid"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setMiddleInitial(rs.getString("middleinitial"));
				customer.setAddress(rs.getString("address"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setZipCode(rs.getInt("zipcode"));
				customer.setPhoneNumber(rs.getString("phonenumber"));
				customer.setUserName(rs.getString("username"));
				customer.setPasswd(rs.getString("passwd"));
				customer.setAdmin(rs.getBoolean("superuser"));
				customer.setKey(rs.getString("secret_key"));
				customer.setAccountNumber(rs.getInt("accountnumber"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customers;
	}

	@Override // works
	public void setCustomer(Customers cu) {
		connection = DatabaseConnection.getConnection();
		String query = "INSERT INTO customers (userid, firstname, lastname, middleinitial, address, city, state, zipcode, phonenumber, username, passwd, superuser, accountnumber, secret_key, is_approved) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, cu.getUserId());
			stmt.setString(2, cu.getFirstName());
			stmt.setString(3, cu.getLastName());
			stmt.setString(4, cu.getMiddleInitial());
			stmt.setString(5, cu.getAddress());
			stmt.setString(6, cu.getCity());
			stmt.setString(7, cu.getState());
			stmt.setInt(8, cu.getZipCode());
			stmt.setString(9, cu.getPhoneNumber());
			stmt.setString(10, cu.getUserName());
			stmt.setString(11, cu.getPasswd());
			stmt.setBoolean(12, cu.isAdmin());
			stmt.setInt(13, cu.getAccountNumber());
			stmt.setString(14, cu.getKey());
			stmt.setBoolean(15, cu.isVerified());
			stmt.executeQuery();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	@Override // works
	public void updateCustomer(Customers cu) {
		connection = DatabaseConnection.getConnection();
		String query = "UPDATE customers SET firstname = ?, lastname = ?, middleinitial = ?, address = ?, city = ?, state = ?, zipcode = ?, phonenumber = ?, username = ?, passwd = ?, superuser = ?, accountnumber = ?, secret_key = ?, is_approved = ? WHERE userid = ?";

		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, cu.getFirstName());
			stmt.setString(2, cu.getLastName());
			stmt.setString(3, cu.getMiddleInitial());
			stmt.setString(4, cu.getAddress());
			stmt.setString(5, cu.getCity());
			stmt.setString(6, cu.getState());
			stmt.setInt(7, cu.getZipCode());
			stmt.setString(8, cu.getPhoneNumber());
			stmt.setString(9, cu.getUserName());
			stmt.setString(10, cu.getPasswd());
			stmt.setBoolean(11, cu.isAdmin());
			stmt.setInt(12, cu.getAccountNumber());
			stmt.setString(13, cu.getKey());
			stmt.setBoolean(14, cu.isVerified());
			stmt.setInt(15, cu.getUserId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}
	
	private void closeResources() {
		try {
			if (connection != null && !stmt.isClosed()) {
				connection.close();
				stmt.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// create main method to test the class
//	public static void main(String[] args) {
//		CustomersDAImpl customerDAOImpl = new CustomersDAImpl();
//		Customers customer = new Customers();
//		customer.setUserId(1);
//		customer.setFirstName("John");
//		customer.setLastName("Doe");
//		customer.setMiddleInitial("M");
//		customer.setAddress("123 Main St");
//		customer.setCity("New York");
//		customer.setState("NY");
//		customer.setZipCode(12345);
//		customer.setPhoneNumber("123-456-7890");
//		customer.setUserName("jdoe");
//		customer.setPasswd("password");
//		customer.setAdmin(false);
//		customer.setAccountNumber(123456789);
//		customerDAOImpl.setCustomer(customer);
//		System.out.println(customerDAOImpl.getCustomer(1));
//		System.out.println(customerDAOImpl.getAllCustomers());
//		customer.setUserId(2);
//		customer.setFirstName("Jane");
//		customer.setLastName("Dayne");
//		customer.setMiddleInitial("M");
//		customer.setAddress("123 Main St");
//		customer.setCity("New York");
//		customer.setState("NY");
//		customer.setZipCode(12345);
//		customer.setPhoneNumber("123-456-7890");
//		customer.setUserName("jdoeyt");
//		customer.setPasswd("password");
//		customer.setAdmin(false);
//		customer.setAccountNumber(234567809);
//		customerDAOImpl.updateCustomer(customer);
//		System.out.println("after update");
//		System.out.println(customerDAOImpl.getCustomer(110104));
//		System.out.println("after getb customer");
		//System.out.println(customerDAOImpl.getAllCustomers());
//		System.out.println("after get all");
//
//		//get list of customers
//		ArrayList<Customers> customers = customerDAOImpl.getAllCustomers();
//		for (Customers c : customers) {
//			System.out.println(c.getFirstName());
//		}
//	}
}
