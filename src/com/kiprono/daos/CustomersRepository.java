package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Customers;

public interface CustomersRepository {
	public Customers getCustomer(int custId);
	
	public ArrayList<Customers> getAllCustomers();

	public void addCustomer(Customers customer);
	
	public Customers getCustomerByUserName(String s);
	
}
