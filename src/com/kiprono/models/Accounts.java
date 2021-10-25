package com.kiprono.models;
import java.util.LinkedList;

/*Accounts class
 * 
 * 
 * */
public class Accounts {
	private Customers accountHolder;
	private double runningBalance;
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
