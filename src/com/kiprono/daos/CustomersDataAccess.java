package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Customers;

public interface CustomersDataAccess {
	// get one customer
	public Customers getCustomer(int userId);
	
	//all customers
	public ArrayList<Customers> getAllCustomers();
	
	// save new customer
	public void setCustomer(Customers cu);

	// update customer
	public void updateCustomer(Customers cu);
	
	//get one customer by user id
	public Customers getCustomerbyUserId(String s);
}
