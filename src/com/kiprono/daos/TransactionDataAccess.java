package com.kiprono.daos;

import java.util.ArrayList;

import com.kiprono.models.Transaction;

public interface TransactionDataAccess {
	// get all transaction
	public ArrayList<Transaction> getAllTransaction();
	//
	//get one transaction
	public Transaction getOneTransaction(String id);
	//set transaction
    public void setTransaction(Transaction transaction);
    //delete transaction
    public void deleteTransaction(String id);
    //update transaction
    public void updateTransaction(Transaction transaction);
    
    //get all transactions in an account
    public ArrayList<Transaction> getAccountTransaction(int accountId);
}
