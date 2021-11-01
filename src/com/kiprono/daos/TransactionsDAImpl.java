package com.kiprono.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kiprono.models.Transaction;
import com.kiprono.utils.*;


public class TransactionsDAImpl implements TransactionDataAccess {
	private Connection connection;
	private PreparedStatement stmt;
	private ResultSet rs;

	@Override
	public ArrayList<Transaction> getAllTransaction() {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM transactions";

		try {
			stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Transaction transaction = new Transaction();
				transaction.setAccountId(rs.getInt("accountid"));
				transaction.setAmount(rs.getDouble("amount"));
				transaction.setTransactionDate(rs.getTimestamp("transactiondate"));
				transaction.setTransactionType(rs.getString("transactiontype"));
				transaction.setTransactionId(rs.getString("transactionid"));
				transaction.setApproved(rs.getBoolean("approved"));
				transactions.add(transaction);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return transactions;
	}

	@Override // done
	public Transaction getOneTransaction(String id) {
		Transaction transaction = new Transaction();
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM transactions WHERE transactionid = ?";
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			rs.next();
			transaction.setAccountId(rs.getInt("accountid"));
			transaction.setAmount(rs.getDouble("amount"));
			transaction.setTransactionDate(rs.getTimestamp("transactiondate"));
			transaction.setTransactionType(rs.getString("transactiontype"));
			transaction.setTransactionId(rs.getString("transactionid"));
			transaction.setApproved(rs.getBoolean("approved"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return transaction;
	}

	@Override // DONE
	public void setTransaction(Transaction transaction) {
		
		connection = DatabaseConnection.getConnection();
		String query = "INSERT INTO transactions (accountid,transactionid, transactiondate ,transactiontype,amount, approved) VALUES (?,?,?,?,?,?)";

		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, transaction.getAccountId());
			stmt.setString(2, transaction.getTransactionId());
			stmt.setTimestamp(3, transaction.getTransactionDate());
			stmt.setString(4, transaction.getTransactionType());
			stmt.setDouble(5, transaction.getAmount());
			stmt.setBoolean(6, transaction.isApproved());
			stmt.executeQuery();
			System.out.println("Success");
			
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			closeResources();
		}
	}

	@Override //working
	public void deleteTransaction(String id) {
		
		connection = DatabaseConnection.getConnection();
		String query = "DELETE FROM transactions WHERE transactionid = ?";
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}

	@Override // working
	public void updateTransaction(Transaction transaction) {
		
		connection = DatabaseConnection.getConnection();
		String query = "UPDATE transactions SET accountid = ?, approved =?, amount = ? WHERE transactionid = ?";
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, transaction.getAccountId());
			stmt.setBoolean(2, transaction.isApproved());
			stmt.setDouble(3, transaction.getAmount());
			stmt.setString(4, transaction.getTransactionId());
			rs = stmt.executeQuery();
			System.out.println("Success");
			
		} catch (SQLException e) {
			//e.printStackTrace();
			
		} finally {
			closeResources();
		}

	}
	
	private void closeResources() {
		try {
			if (connection != null && !stmt.isClosed()) {
				connection.close();
				stmt.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Transaction> getAccountTransaction(int accountId) {
		connection = DatabaseConnection.getConnection();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		String query = "SELECT * FROM transactions WHERE accountid = ?";
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, accountId);
			rs = stmt.executeQuery();
			while(rs.next()){
				Transaction transaction = new Transaction();
				transaction.setAccountId(rs.getInt("accountid"));
				transaction.setAmount(rs.getDouble("amount"));
				transaction.setTransactionDate(rs.getTimestamp("transactiondate"));
				transaction.setTransactionType(rs.getString("transactiontype"));
				transaction.setTransactionId(rs.getString("transactionid"));
				transaction.setApproved(rs.getBoolean("approved"));
				transactions.add(transaction);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return transactions;
	}

	// create main method to test the class
//	public static void main(String[] args) {
//		TransactionsDAImpl dao = new TransactionsDAImpl();
//		Transaction transaction = new Transaction();
//	// 	// transaction.setAccountId(102015);
//	// 	// transaction.setAmount(100);
//	// 	// transaction.setTransactionDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//	// 	// transaction.setTransactionType("deposit");
//	// 	// transaction.setTransactionId("12345A");
//	// 	// transaction.setApproved(true);
//	// 	// dao.setTransaction(transaction);
//		
//	// 	// Transaction transaction2 = new Transaction();
//	// 	// transaction2.setAccountId(102015);
//	// 	// transaction2.setAmount(100);
//	// 	// transaction2.setTransactionDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//	// 	// transaction2.setTransactionType("deposit");
//	// 	// transaction2.setTransactionId("12346A");
//	// 	// transaction2.setApproved(true);
//	// 	// dao.setTransaction(transaction2);
//		
//	// 	// Transaction transaction3 = new Transaction();
//	// 	// transaction3.setAccountId(103749);
//	// 	// transaction3.setAmount(100);
//	// 	// transaction3.setTransactionDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//	// 	// transaction3.setTransactionType("deposit");
//	// 	// transaction3.setTransactionId("12347A");
//	// 	// transaction3.setApproved(false);
//	// 	// dao.setTransaction(transaction3);
//		
//	// 	// Transaction transaction4 = new Transaction();
//	// 	// transaction4.setAccountId(131595);
//	// 	// transaction4.setAmount(350);
//	// 	// transaction4.setTransactionDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//	// 	// transaction4.setTransactionType("Transfer");
//	// 	// transaction4.setTransactionId("T0023A");
//	// 	// transaction4.setApproved(false);
//	// 	// dao.setTransaction(transaction4);
//
//	// 	// get one transaction
//	// 	transaction = dao.getOneTransaction("12345A");
//	// 	System.out.println(transaction.getAccountId());
//	// 	System.out.println(transaction.getAmount());
//	// 	System.out.println(transaction.getTransactionDate());
//	// 	System.out.println(transaction.getTransactionType());
//	// 	System.out.println(transaction.getTransactionId());
//	// 	System.out.println(transaction.isApproved());
//
//	// 	// test delete transaction
//	// 	dao.deleteTransaction("12346");
//
//	// 	// test update transaction to approved
//	// 	transaction.setApproved(false);
//	// 	dao.updateTransaction(transaction);
//		
//	// 	// test get all transactions
//		ArrayList<Transaction> transactions = dao.getAccountTransaction(102015);
//		for(Transaction t : transactions){
//			System.out.println(t.getAccountId());
//			System.out.println(t.getAmount());
//			System.out.println(t.getTransactionDate());
//			System.out.println(t.getTransactionType());
//			System.out.println(t.getTransactionId());
//			System.out.println(t.isApproved());
//		}
//	}
}
