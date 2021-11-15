package com.kiprono.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kiprono.models.Transaction;
import com.kiprono.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionsDAImpl implements TransactionDataAccess {
	private Connection connection;
	private PreparedStatement stmt;
	private ResultSet rs;
	private static final Logger LOG = LogManager.getLogger(TransactionsDAImpl.class);

	@Override
	public ArrayList<Transaction> getAllTransaction() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": all transactions Accessed");
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		} finally {
			closeResources();
		}
		return transactions;
	}

	@Override // done
	public Transaction getOneTransaction(String id) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": transaction Accessed");
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		} finally {
			closeResources();
		}
		return transaction;
	}

	@Override // DONE
	public void setTransaction(Transaction transaction) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Transaction set");
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		} finally {
			closeResources();
		}
	}

	@Override //working
	public void deleteTransaction(String id) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": transaction deleted");
		connection = DatabaseConnection.getConnection();
		String query = "DELETE FROM transactions WHERE transactionid = ?";
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		} finally {
			closeResources();
		}
	}

	@Override // working
	public void updateTransaction(Transaction transaction) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": transaction updated");
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
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
			
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
	}

	@Override
	public ArrayList<Transaction> getAccountTransaction(int accountId) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": All transactions accessed");
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		} finally {
			closeResources();
		}
		return transactions;
	}

	@Override
	public ArrayList<Transaction> getReceivedTransactions(int accountId) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": All transactions accessed");
		connection = DatabaseConnection.getConnection();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		String type = "Receive";
		
		String query = "SELECT * FROM transactions WHERE accountid = ? and transactiontype = ?";
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, accountId);
			stmt.setString(2, type);
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
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		} finally {
			closeResources();
		}
		return transactions;
	}

}
