package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Accounts;

public interface AccountsRepository {
	public Accounts getAccount(int accId);
	
	public ArrayList<Accounts> getAllAccounts();
	
	public void updateAccount(Accounts account);
	
	public void createAccount(Accounts account);
}
