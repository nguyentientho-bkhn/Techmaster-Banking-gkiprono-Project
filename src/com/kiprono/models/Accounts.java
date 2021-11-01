package com.kiprono.models;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*Accounts class
 * 
 * 
 * */
public class Accounts {
	private Customers accountHolder;
	private double runningBalance;
	private int accountNumber;
	private int accId;
	
	@SuppressWarnings("unused")
	private List<Transaction> transactions = new LinkedList<Transaction>();
	
	
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<Transaction> transactions2) {
		this.transactions = transactions2;
	}
	// List of transactions
		
	public Customers getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(Customers accountHolder) {
		this.accountHolder = accountHolder;
	}
	public double getRunningBalance() {
		return runningBalance;
	}
	public void setRunningBalance(double runningBalance) {
		this.runningBalance = runningBalance;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	// TODO
	/*
	 * append to transaction
	 * lookup transaction
	 * reverse transaction
	 * constructor
	 * */
}
