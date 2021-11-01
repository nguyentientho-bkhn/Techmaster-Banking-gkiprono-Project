package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Customers;

public class UsersRepoImpl implements UsersRepo {
	private CustomersDataAccess customerData = new CustomersDAImpl();
	private AccountsData accData = new AccountsDAImpl();

	@Override
	public Customers getCustomer(int id) {
		Customers customer = new Customers();
		

		customer = customerData.getCustomer(id);
		customer.setCustomerAccount(accData.getAccount(id));


		return customer;
	}

	@Override
	public ArrayList<Customers> getAllCustomers() {
		ArrayList<Customers> customers = new ArrayList<Customers>();
		customers = customerData.getAllCustomers();

		for (Customers customer : customers) {
			customer.setCustomerAccount(accData.getAccount(customer.getAccountNumber()));
		}


		return customers;
	}

}
