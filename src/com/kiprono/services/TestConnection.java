package com.kiprono.services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kiprono.models.Customers;

@SuppressWarnings("unused")
public class TestConnection {
	private Statement stmt;
	private static Customers customer = new Customers();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection connection = DatabaseConnection.getConnection();
			Statement statement = connection.createStatement();) {
			Class.forName("org.postgresql.Driver");
			
			ResultSet results = statement.executeQuery("SELECT * FROM customers");
			
			
			while(results.next()) {
				int id = results.getInt("userId");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String userName = results.getString("username");
				String password = results.getString("passwd");
				boolean sUser = results.getBoolean("superUser");
				int accNumber = results.getInt("accountNumber");
				
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setUserName(userName);
				customer.setPasswd(password);
				customer.setSudoer(sUser);
				customer.setUserId(id);

				printCustomer(customer);			
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			
		}

		
		
	}
	// print customer to console
	public static void printCustomer(Customers customer) {
		System.out.println(customer.getFirstName() + " | " + customer.getLastName() + " | " + customer.getUserName() + " | " + customer.getPasswd() + " | " + customer.isSudoer() + " | " + customer.getUserId());
	}

}
