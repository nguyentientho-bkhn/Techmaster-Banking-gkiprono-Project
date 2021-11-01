package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Accounts;
import com.kiprono.models.Customers;

public class CustomersRepoImpl implements CustomersRepository {
	private CustomersDataAccess cutomerDa = new CustomersDAImpl();
	private AccountsData accounts = new AccountsDAImpl();
	
	@Override
	public Customers getCustomer(int custId) {
		// TODO Auto-generated method stub
		Customers customer = cutomerDa.getCustomer(custId);
		Accounts account = accounts.getAccount(custId);
		customer.setCustomerAccount(account);
		
		return customer;
	}

	@Override
	public ArrayList<Customers> getAllCustomers() {
		// TODO Auto-generated method stub
		ArrayList<Customers> customers = cutomerDa.getAllCustomers();
		for(Customers customer : customers) {
			Accounts account = accounts.getAccount(customer.getAccountNumber());
			customer.setCustomerAccount(account);
		}
		
		return customers;
	}

	@Override
	public void addCustomer(Customers customer) {
		// TODO Auto-generated method stub
		cutomerDa.setCustomer(customer);
		accounts.setAccount(customer.getCustomerAccount());
		//accounts.addAccount(customer.getAccountNumber(), customer.getAccountType());
	}
}
