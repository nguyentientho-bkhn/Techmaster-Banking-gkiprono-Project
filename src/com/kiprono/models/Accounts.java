package com.kiprono.models;
import java.util.LinkedList;

/*Accounts class
 * 
 * 
 * */
public class Accounts {
	private Customers accountHolder;
	private float runningBalance;
	private int accountNumber;
	
	// List of transactions
	@SuppressWarnings("unused")
	private LinkedList<Transaction> transactions = new LinkedList<Transaction>();
	
	
	public Customers getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(Customers accountHolder) {
		this.accountHolder = accountHolder;
	}
	public float getRunningBalance() {
		return runningBalance;
	}
	public void setRunningBalance(float runningBalance) {
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
	 * */
}
