package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Customers;

public interface UsersRepo {
	public Customers getCustomer(int id);
	public ArrayList<Customers> getAllCustomers();
}
