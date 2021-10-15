package com.kiprono.models;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class Transaction {
	// private attr
	@SuppressWarnings("unused")
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@SuppressWarnings("unused")
	private int transactionId;
	
	//@SuppressWarnings("unused")
	//private Customers customer;
	
	@SuppressWarnings("unused")
	private Date transactionDate;
	
	@SuppressWarnings("unused")
	private enum transactionType {DEPOSIT, WITHDRAW, LOAN}
	
	public void setTransactionId(int a) {this.transactionId = a;}
	public int getTransactionId() {return this.transactionId;}
}
