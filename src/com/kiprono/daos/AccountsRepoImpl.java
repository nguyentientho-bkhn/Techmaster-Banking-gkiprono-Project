package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Accounts;
import com.kiprono.models.Transaction;

public class AccountsRepoImpl implements AccountsRepository {
	private AccountsData accountsData = new AccountsDAImpl();
	private TransactionDataAccess transactionData = new TransactionsDAImpl();


	@Override
	public Accounts getAccount(int accId) {
		// TODO Auto-generated method stub
		Accounts account = accountsData.getAccount(accId);
		ArrayList<Transaction> transactions = transactionData.getAccountTransaction(accId);
		account.setTransactions(transactions);


		return account;
	}

	@Override
	public ArrayList<Accounts> getAllAccounts() {
		ArrayList<Accounts> accounts = accountsData.getAllAccounts();
		for (Accounts account : accounts) {
			ArrayList<Transaction> transactions = transactionData.getAccountTransaction(account.getAccId());
			account.setTransactions(transactions);
		}

		return accounts;
	}

	@Override
	public void updateAccount(Accounts account) {
		// TODO Auto-generated method stub
		accountsData.UpdateAccount(account);
	}

	@Override
	public void createAccount(Accounts account) {
		accountsData.setAccount(account);
		
	}

}
