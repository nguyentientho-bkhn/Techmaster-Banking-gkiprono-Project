package com.kiprono.states;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.kiprono.controllers.SignUp;
import com.kiprono.daos.CustomersDAImpl;
import com.kiprono.daos.CustomersDataAccess;
import com.kiprono.daos.CustomersRepoImpl;
import com.kiprono.daos.CustomersRepository;
import com.kiprono.daos.TransactionDataAccess;
import com.kiprono.daos.TransactionsDAImpl;
import com.kiprono.models.Accounts;
import com.kiprono.models.Customers;
import com.kiprono.models.Transaction;
import com.kiprono.utils.Keyboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * while here, user can deposit, withdraw, perform account-account transfer, accept transfer
 * or change personal details
 * employees can do that too
 * */

@SuppressWarnings("unused")
public class LoggedInState implements UserState {
	private static final Logger LOG = LogManager.getLogger(LoggedInState.class);
	
	Keyboard keyboard = Keyboard.getKeyboard();
	private Customers customer;
	Accounts account;
	List<Transaction> transaction;
	TransactionDataAccess transactionDataAccess = new TransactionsDAImpl();
	ArrayList<Transaction> allTrans = new ArrayList<Transaction>();
	CustomersRepository customerRepository = new CustomersRepoImpl();
	ArrayList<Customers> allCustomers = new ArrayList<Customers>();
	CustomersDataAccess cutomerInfo = new CustomersDAImpl();
	
	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
		this.account = customer.getCustomerAccount();
		this.transaction = account.getTransactions();
	}

	private LoggedInState instance;
	public LoggedInState() { 
		// TODO Auto-generated constructor stub
		//instance = new LoggedInState();
	}
	
	public LoggedInState getLoggedInInstance() {
		return this.instance;
	}

	@Override
	public void updateState(Context context) {
		// TODO Auto-generated method stub
		context.setState(this);
	}

	@Override
	public void gotoState() {
		LOG.trace("Logged in" + String.valueOf(System.currentTimeMillis()));
		// TODO Auto-generated method stub
		// check if user is super user
		if(customer.isAdmin()) {
			LOG.debug(String.valueOf(System.currentTimeMillis()) + ": isAdmin loaded");
			otherUsersMenu();
			LOG.debug(String.valueOf(System.currentTimeMillis()) + ": isAdmin exit" );
		}else {
			LOG.debug(String.valueOf(System.currentTimeMillis()) + ": user start" );
			mainMenu();
			LOG.debug(String.valueOf(System.currentTimeMillis()) + ": user end");
		}
	}

	private void mainMenu() {
		// TODO Auto-generated method stub
		int choice = customerMenu();
		switch(choice) {
		case 1:
			viewBalance();
			break;
		case 2:
			withdrawCash();
			break;
		case 3:
			depositCash();
			break;
		case 4:
			sendMoney();
			break;
		case 5:
			acceptMoneyTransfer();
			break;

		case 6:	
			//logout
			System.out.println("Logging out...");
			break;
		}
	}

	private void otherUsersMenu() {
		// TODO Auto-generated method stub
		int choice = superUserMenu();
		switch(choice) {
		case 1:
			viewBalance();
			break;
		case 2:
			withdrawCash();
			break;
		case 3:
			depositCash();
			break;
		case 4:
			sendMoney();
			break;
		case 5:
			acceptMoneyTransfer();
			break;
		case 6:
			listAllTransactions();
			break;
		case 7:
			openAccount();
			break;
		case 8:
			checkAccount();
			break;
		case 9:
			approveAccount();
			break;
		case 10:
			//logout
			System.out.println("Logging out...");
			break;
		}
	}

	private int customerMenu() {
		int choice  = 0;
		System.out.println("******************** Welcome **************** ");
		System.out.println("1. View Balance");
		System.out.println("2. Withdraw cash");
		System.out.println("3. Deposit cash");
		System.out.println("4. Send money");
		System.out.println("5. Accept money transfer");
		System.out.println("6. Exit");
		
		
		choice = keyboard.readInt("Enter 1-5: ");
		while(choice < 1 || choice > 6) {
			System.out.print("Invalid choice!! ");
			choice = keyboard.readInt("Enter 1-6: ");
		}

		return choice;
	}
	
	private int superUserMenu() {
		int choice  = 0;
		System.out.println("******************** Welcome **************** ");
		System.out.println("1. View Balance");
		System.out.println("2. Withdraw cash");
		System.out.println("3. Deposit cash");
		System.out.println("4. Send money");
		System.out.println("5. Accept money transfer");
		System.out.println("6. View all transactions");
		System.out.println("7. Open an account");
		System.out.println("8. Check bank accout");
		System.out.println("9. Approve/Reject accounts");
		
		
		choice = keyboard.readInt("Enter 1-5: ");
		while(choice < 1 || choice > 9) {
			System.out.print("Invalid choice!! ");
			choice = keyboard.readInt("Enter 1-9: ");
		}
		
		return choice;
	}
	
	// Balance
	private void viewBalance() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Viewed Balance");
		System.out.println("******************** Balance **************** ");
		System.out.println("Balance: " + customer.getCustomerAccount().getRunningBalance());
		System.out.println("******************** Balance **************** ");
	}

	// withdraw cash
	private void withdrawCash() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Withdrawal");
		System.out.println("******************** Withdraw **************** ");
		int amount;
		boolean invalid = false;
		Transaction newTransaction = new Transaction();
		do{
			amount = keyboard.readInt("Enter amount: ");
			if(amount > account.getRunningBalance()) {
				System.out.println("Insufficient balance!! ");
			} else if (amount < 0) {
				System.out.println("Invalid amount!! ");
				invalid = true;
				LOG.error(String.valueOf(System.currentTimeMillis()) + ": Insufficient balance to perform transaction");
			} else {
				account.setRunningBalance(account.getRunningBalance()-amount);
				System.out.println("Withdraw successful!! ");
				invalid = true;
				newTransaction.setAmount(amount);
				newTransaction.setTransactionType("Withdraw");
				newTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				newTransaction.setTransactionId(generateRandomString(8));
				account.getTransactions().add(newTransaction);
			}
		} while(!invalid);
		
		System.out.println("******************** Withdraw **************** ");
	}

	private static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	// deposit cash
	private void depositCash() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Deposit");
		System.out.println("******************** Deposit **************** ");
		int amount;
		boolean invalid = false;
		Transaction newTransaction = new Transaction();
		do{
			amount = keyboard.readInt("Enter amount: ");
			if(amount < 0) {
				System.out.println("Invalid amount!! ");
				invalid = true;
				LOG.error(String.valueOf(System.currentTimeMillis()) + ": Insufficient balance to perform transaction");
			} else {
				account.setRunningBalance(account.getRunningBalance()+amount);
				System.out.println("Deposit successful!! ");
				invalid = true;
				newTransaction.setAmount(amount);
				newTransaction.setTransactionType("Deposit");
				newTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				newTransaction.setTransactionId(generateRandomString(8));
				account.getTransactions().add(newTransaction);
			}
		} while(!invalid);
		
		System.out.println("******************** Deposit **************** ");
	}

	// send money
	private void sendMoney() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Money sent");
		System.out.println("******************** Send Money **************** ");
		int amount;
		boolean invalid = false;
		Transaction newTransaction = new Transaction();
		do{
			amount = keyboard.readInt("Enter amount: ");
			if(amount > account.getRunningBalance()) {
				System.out.println("Insufficient balance!! ");
			} else if (amount < 0) {
				System.out.println("Invalid amount!! ");
				invalid = true;
				LOG.error(String.valueOf(System.currentTimeMillis()) + ": Insufficient balance to perform transaction");
			} else {
				account.setRunningBalance(account.getRunningBalance()-amount);
				System.out.println("Send successful!! ");
				invalid = true;
				newTransaction.setAmount(amount);
				newTransaction.setTransactionType("Send");
				newTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				newTransaction.setTransactionId(generateRandomString(8));
				account.getTransactions().add(newTransaction);
			}
		} while(!invalid);
		
		System.out.println("******************** Send Money **************** ");
	}

	// accept money transfer
	private void acceptMoneyTransfer() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Accept Money");
		System.out.println("******************** Accept Money Transfer **************** ");
		int amount;
		boolean invalid = false;
		Transaction newTransaction = new Transaction();
		do{
			amount = keyboard.readInt("Enter amount: ");
			if(amount < 0) {
				System.out.println("Invalid amount!! ");
				invalid = true;
				LOG.error(String.valueOf(System.currentTimeMillis()) + ": insifficient money");
			} else {
				account.setRunningBalance(account.getRunningBalance()+amount);
				System.out.println("Accept successful!! ");
				invalid = true;
				newTransaction.setAmount(amount);
				newTransaction.setTransactionType("Accept");
				newTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				newTransaction.setTransactionId(generateRandomString(8));
				account.getTransactions().add(newTransaction);
			}
		} while(!invalid);
		
		System.out.println("******************** Accept Money Transfer **************** ");
	}

	// view all transactions
	private void viewAllTransactions() {
		LOG.debug(String.valueOf(System.currentTimeMillis()) + ": List transactions");
		System.out.println("******************** View All Transactions **************** ");
		for(Transaction transaction : account.getTransactions()) {
			System.out.println(transaction.getTransactionId() + " " + transaction.getTransactionType() + " " + transaction.getAmount() + " " + transaction.getTransactionDate());
		}
		System.out.println("******************** View All Transactions **************** ");

	}

	// view all transactions log
	private void listAllTransactions(){
		LOG.debug(String.valueOf(System.currentTimeMillis()) + ": List transactions");
		allTrans = transactionDataAccess.getAllTransaction();
		System.out.println("******************** List All Transactions **************** ");
		for(Transaction transaction : allTrans) {
			System.out.println(transaction.getTransactionId() + " " + transaction.getTransactionType() + " " + transaction.getAmount() + " " + transaction.getTransactionDate());
		}
		System.out.println("******************** List All Transactions **************** ");

	}

	// check a bank account
	private void checkAccount() {
		// ask for user id
		LOG.debug(String.valueOf(System.currentTimeMillis()) + ": Account checked");
		
		System.out.println("******************** Check Account **************** ");
		int userId = keyboard.readInt("Enter user id: ");
		Customers currentCustomer = customerRepository.getCustomer(userId);
		Accounts currentAccount = currentCustomer.getCustomerAccount();
		if(currentCustomer != null) {
			System.out.println("Names: " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName());
			System.out.println("Account Number: " + currentAccount.getAccountNumber());
			System.out.println("Account Balance: " + currentAccount.getRunningBalance());
			System.out.println("******************** Check Account **************** ");
		} else {
			System.out.println("Invalid user id!! ");
		}
		
	}

	// open an account
	private void openAccount() {
		LOG.debug(String.valueOf(System.currentTimeMillis()) + ": Account created");
		System.out.println("******************** Open Account **************** ");
		SignUp.addCustomer();
	}

	// approve an Transaction
	private void approveTransaction() {
		LOG.debug(String.valueOf(System.currentTimeMillis()) + ": Approving transaction");
		System.out.println("******************** Approve Transaction **************** ");
		allTrans = transactionDataAccess.getAllTransaction();
		
		for (Transaction transaction : allTrans) {
			if(!transaction.isApproved()){
				System.out.println("Transaction Id: " + transaction.getTransactionId());
				System.out.println("Transaction Type: " + transaction.getTransactionType());
				System.out.println("Transaction Amount: " + transaction.getAmount());
				System.out.println("Transaction Date: " + transaction.getTransactionDate());
				System.out.println("******************** Approve Transaction **************** ");
				
				int approve = keyboard.readInt("Enter 1 to approve or 0 to reject: ");
				if(approve == 1) {
					transaction.setApproved(true);
					transactionDataAccess.updateTransaction(transaction);
					System.out.println("Transaction approved!! ");
				} else {
					System.out.println("Transaction rejected!! ");
				}
			}
		}
	}

	// aprove an account
	private void approveAccount() {
		LOG.debug(String.valueOf(System.currentTimeMillis()) + ": Approving the account");
		System.out.println("******************** Approve Account **************** ");
		allCustomers = customerRepository.getAllCustomers();
		for (Customers customer : allCustomers) {
			if(!customer.isAdmin()) {
				System.out.println("Customer Id: " + customer.getUserId());
				System.out.println("Customer Name: " + customer.getFirstName() + " " + customer.getLastName());
				System.out.println("******************** Approve Account **************** ");
				
				int approve = keyboard.readInt("Enter 1 to approve or 0 to reject: ");
				if(approve == 1) {
					customer.setVerified(true);
					cutomerInfo.updateCustomer(customer);
					System.out.println("Customer approved!! ");
				} else {
					System.out.println("Customer rejected!! ");
				}
			}
		}
	}
}
