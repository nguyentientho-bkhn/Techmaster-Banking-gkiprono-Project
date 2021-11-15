package com.kiprono.daos;

import com.kiprono.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kiprono.models.Customers;
import com.kiprono.states.LoggedInState;

public class CustomersDAImpl implements CustomersDataAccess {
	private Connection connection;
	private PreparedStatement stmt;
	private ResultSet rs;
	private static final Logger LOG = LogManager.getLogger(CustomersDAImpl.class);

	@Override // works
	public Customers getCustomer(int userId) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": cuatomer Accessed, getCustomer()");
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
//			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customer;
	}

	@Override // works
	public ArrayList<Customers> getAllCustomers() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Get all customers");
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
			//e.printStackTrace();
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
//			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customers;
	}

	@Override // works
	public void setCustomer(Customers cu) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Customer set");
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
//			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	@Override // works
	public void updateCustomer(Customers cu) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Customer updated");
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
//			e.printStackTrace();
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
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

//			e.printStackTrace();
			LOG.trace(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
	}

	@Override
	public Customers getCustomerbyUserId(String s) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": cuatomer Accessed, getCustomer()");
		Customers customer = new Customers();
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM customers WHERE username = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, s);
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
//			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customer;
	}

}
