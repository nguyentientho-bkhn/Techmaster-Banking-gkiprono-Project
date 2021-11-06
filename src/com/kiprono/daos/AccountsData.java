package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Accounts;

public interface AccountsData {
	// get one account
	public Accounts getAccount(int accId);
	
	//all accounts
	public ArrayList<Accounts> getAllAccounts();
	
	//set account
	public  void setAccount(Accounts acc);
	
	// update an account
	public void UpdateAccount(Accounts acc);
	
}
