package com.kiprono.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.kiprono.utils.*;
import com.kiprono.models.*;

public class AccountsDAImpl implements AccountsData {
	private Connection connection = null;
	private PreparedStatement stmt;

	@Override // done
	public Accounts getAccount(int accId) {
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
			e.printStackTrace();
		}
				
		closeResources();
		return account;
	}

	@Override
	public ArrayList<Accounts> getAllAccounts() {
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
			e.printStackTrace();
		}
		
		// close connection
		closeResources();

		return accounts;
	}

	@Override // done
	public void setAccount(Accounts acc) {
		// add account to database
		connection = DatabaseConnection.getConnection();
		String query = "INSERT INTO accounts (accountid,accountnumber, runningbalance) VALUES (?,?, ?)";
		//stmt.setInt(1, acc.getAccId());
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, acc.getAccId());
			stmt.setInt(2, acc.getAccountNumber());
			stmt.setDouble(3, acc.getRunningBalance());
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("this generated exception");
		}
		
		// close connection
		closeResources();
	}

	@Override // done
	public void UpdateAccount(Accounts acc) {
		// update account in database

		try{
			connection = DatabaseConnection.getConnection();
			String query = "UPDATE accounts SET runningbalance = ? WHERE accountnumber = ?";
			stmt = connection.prepareStatement(query);
			stmt.setDouble(1, acc.getRunningBalance());
			stmt.setInt(2, acc.getAccountNumber());
			stmt.executeUpdate();
			System.out.println("Success");
		}
		catch(Exception e){
			System.out.println(e);
		}
		// close connection
		closeResources();
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

	// create tostring method for accounts list
	

//	// create main method to test
//	public static void main(String[] args) {
//		AccountsData accountsDA = new AccountsDAImpl();
//		Accounts account1 = new Accounts();
//		Accounts account = new Accounts();
//		//account.setAccId(1);
//		account.setAccId(102627);
//		account.setRunningBalance(1000.00);
//		account.setAccountNumber(1356000);
//		//accountsDA.UpdateAccount(account);
//		
//		//accountsDA.setAccount(account);
//		account1 = accountsDA.getAccount(1356000);
//		//accountsDA.UpdateAccount(account);
//		//System.out.println(accountsDA.getAccount(1));
//		
//		System.out.println(account1.getRunningBalance());
//		
//		ArrayList<Accounts> accounts = accountsDA.getAllAccounts();
//		//System.out.println(accountsDA.getAllAccounts());
//		System.out.println("ACCOUNT ID	|ACCOUNT NUMBER		|RUNNING BALANCE	");
//		for (Accounts acc : accounts) {
//			
//			System.out.println(acc.getAccId()+"		|"+acc.getAccountNumber()+"		|"+acc.getRunningBalance());
//		}
//	}

}
