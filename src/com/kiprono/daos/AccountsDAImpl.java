package com.kiprono.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.kiprono.models.Accounts;
import com.kiprono.models.Transaction;
import com.kiprono.utils.DatabaseConnection;

public class AccountsDAImpl implements AccountsData {
	private Connection connection = null;
	private PreparedStatement stmt;
	private static TransactionDataAccess transDa = new TransactionsDAImpl();
	private static final Logger LOG = LogManager.getLogger(AccountsDAImpl.class);


	@Override // done
	public Accounts getAccount(int accId) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Account Accessed");
		Accounts account = new Accounts();
		ResultSet rs;
		// query database
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM accounts WHERE accountnumber = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, accId);
			rs = stmt.executeQuery();
						
			rs.next();
			account.setAccId(rs.getInt("accountid"));
			account.setRunningBalance(rs.getDouble("runningbalance"));
			account.setAccountNumber(rs.getInt("accountnumber"));
			
		} catch (SQLException e) {
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
				
		closeResources();
		return account;
	}

	@Override
	public ArrayList<Accounts> getAllAccounts() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": All accounts accessed, getAllAccounts()");
		ArrayList<Accounts> accounts = new ArrayList<Accounts>();
		// query database
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM accounts";
		try {
			stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			// set account object
			while (rs.next()) {
				Accounts account = new Accounts();
				account.setAccId(rs.getInt("accountid"));
				account.setRunningBalance(rs.getDouble("runningbalance"));
				account.setAccountNumber(rs.getInt("accountnumber"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
		// close connection
		closeResources();

		return accounts;
	}

	@Override // done
	public void setAccount(Accounts acc) {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Account set, setAccount()");
		// add account to database
		connection = DatabaseConnection.getConnection();
		String query = "INSERT INTO accounts (accountid, runningbalance,accountnumber) VALUES (?,?, ?)";
		//stmt.setInt(1, acc.getAccId());
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, acc.getAccId());
			stmt.setDouble(2, acc.getRunningBalance());
			stmt.setInt(3, acc.getAccountHolder().getAccountNumber());
			stmt.execute();
			System.out.println("Account added");
			setTransaction(acc);
			System.out.println("Transaction added successfully");
		} catch (SQLException e) {
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
		
		// close connection
		closeResources();
	}

	@Override // done
	public void UpdateAccount(Accounts acc) {
		// update account in database
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Account updated ");

		try{
			connection = DatabaseConnection.getConnection();
			String query = "UPDATE accounts SET runningbalance = ? WHERE accountnumber = ?";
			stmt = connection.prepareStatement(query);
			stmt.setDouble(1, acc.getRunningBalance());
			stmt.setInt(2, acc.getAccountNumber());
			stmt.executeUpdate();
			System.out.println("Account created successfully");
			
		}
		catch(Exception e){
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
		// close connection
		closeResources();
	}
	// set new transaction
	private static void setTransaction(Accounts acc){
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setAccountId(acc.getAccId());
		transaction.setTransactionId(generateRandomString(8));
		transaction.setAmount(acc.getRunningBalance());
		transaction.setTransactionType("Deposit");
		transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
		trans.add(transaction);
		
		transDa.setTransaction(transaction);

	}

	// randomly generate characters and numbers
	private static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
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

	// create tostring method for accounts list
	

//	// create main method to test
//	public static void main(String[] args) {
////		AccountsData accountsDA = new AccountsDAImpl();
////		Accounts account1 = new Accounts();
//////		Accounts account = new Accounts();
//////		//account.setAccId(1);
////		account1.setAccId(102015);
////		account1.setRunningBalance(1000.00);
////		account1.setAccountNumber(1356000);
//////		//accountsDA.UpdateAccount(account);
////		setTransaction(account1);
//////		//accountsDA.setAccount(account);
//////		account1 = accountsDA.getAccount(1356000);
//////		//accountsDA.UpdateAccount(account);
//////		//System.out.println(accountsDA.getAccount(1));
//////		
//////		System.out.println(account1.getRunningBalance());
//////		
//////		ArrayList<Accounts> accounts = accountsDA.getAllAccounts();
//////		//System.out.println(accountsDA.getAllAccounts());
//////		System.out.println("ACCOUNT ID	|ACCOUNT NUMBER		|RUNNING BALANCE	");
//////		for (Accounts acc : accounts) {
//////			
//////			System.out.println(acc.getAccId()+"		|"+acc.getAccountNumber()+"		|"+acc.getRunningBalance());
////		}
//	}

}
