package com.kiprono.models;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

public class Transaction {
	// private attr
	@SuppressWarnings("unused")
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@SuppressWarnings("unused")
	private String transactionId;
	private int accountId;
	
	//amount
	private double amount;

	//approved
	private boolean approved;

	// type of transaction
	private String transactionType;

	// getters and setters for approved
	public boolean isApproved() {
		return approved;
	}

	@SuppressWarnings("unused")
	private Timestamp transactionDate;

	// date variablein hh:mm:ss format
	
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	//@SuppressWarnings("unused")
	//private Customers customer;
	
	
	public void setTransactionId(String a) {this.transactionId = a;}
	public String getTransactionId() {return this.transactionId;}
}
