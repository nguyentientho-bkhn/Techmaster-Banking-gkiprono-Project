package com.kiprono.states;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.kiprono.controllers.SignUp;
import com.kiprono.daos.AccountsRepoImpl;
import com.kiprono.daos.AccountsRepository;
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
import com.kiprono.views.MainMenu;

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
	private Accounts account;
	private List<Transaction> transaction;
	private TransactionDataAccess transactionDataAccess = new TransactionsDAImpl();
	private ArrayList<Transaction> allTrans = new ArrayList<Transaction>();
	private CustomersRepository customerRepository = new CustomersRepoImpl();
	private ArrayList<Customers> allCustomers = new ArrayList<Customers>();
	private CustomersDataAccess cutomerInfo = new CustomersDAImpl();
	private AccountsRepository accountRepo = new AccountsRepoImpl();
	
	
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
		if(this.customer == null) {
			return;
		}else {
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
	}

	private void mainMenu() {
		// TODO Auto-generated method stub
		int choice = 0;
		
		do {
			choice = customerMenu();
			
			switch (choice) {
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
				openAccount();
				break;
			case 7:
				//logout
				System.out.println("Logging out...");
				MainMenu.loggedIn = false;
				//MainMenu.ContextMenu();
				
				break;
			}
		} while (choice !=7);
	}

	private void otherUsersMenu() {
		int choice = 0;
		do {
			// TODO Auto-generated method stub
			choice = superUserMenu();
			
			switch (choice) {
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
				MainMenu.loggedIn = false;
				//MainMenu.ContextMenu();
				break;
			}
		} while (choice !=10);
	}

	private int customerMenu() {
		int choice  = 0;
		System.out.println("******************** Welcome **************** ");
		System.out.println("1. View Balance");
		System.out.println("2. Withdraw cash");
		System.out.println("3. Deposit cash");
		System.out.println("4. Send money");
		System.out.println("5. Accept money transfer");
		System.out.println("6. Open an account");
		System.out.println("7. Exit");
		
		
		choice = keyboard.readInt("Enter 1-7: ");
		while(choice < 1 || choice > 7) {
			System.out.print("Invalid choice!! ");
			choice = keyboard.readInt("Enter 1-7: ");
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
		System.out.println("10. Exit");
		
		
		choice = keyboard.readInt("Enter 1-10: ");
		while(choice < 1 || choice > 10) {
			System.out.print("Invalid choice!! ");
			choice = keyboard.readInt("Enter 1-10: ");
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
				newTransaction.setAccountId(account.getAccId());
				newTransaction.setAmount(amount);
				newTransaction.setTransactionType("Withdraw");
				newTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				newTransaction.setTransactionId(generateRandomString(8));
				account.getTransactions().add(newTransaction);
				
				//update transaction in the database
				transactionDataAccess.setTransaction(newTransaction);
				//update the account in the database
				accountRepo.updateAccount(account);
			}
		} while(!invalid);
		
		System.out.println("******************** Withdraw **************** ");
	}

	private static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "ab0c1def2ghi3jkl5mno4pqr6stu7vwx8yz9ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int max = characters.length();
		int min = length-2;
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * (max-min) + min);
			sb.append(characters.charAt(index));
		}
		return sb.toString() + String.valueOf(System.currentTimeMillis());
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
				newTransaction.setAccountId(account.getAccId());
				newTransaction.setAmount(amount);
				newTransaction.setTransactionType("Deposit");
				newTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				newTransaction.setTransactionId(generateRandomString(8));
				account.getTransactions().add(newTransaction);
				
				//update transaction in the database
				transactionDataAccess.setTransaction(newTransaction);
				//update the account in the database
				accountRepo.updateAccount(account);
			}
		} while(!invalid);
		
		System.out.println("******************** Deposit **************** ");
	}

	// send money
	private void sendMoney() {
		int accNo;
		Accounts recipientAccount = new Accounts();
		System.out.println("******************** Send Money **************** ");
		int amount;
		boolean invalid = true;
		Transaction newTransaction = new Transaction();
		Transaction recevTransact = new Transaction();
		
		do{
			amount = keyboard.readInt("Enter amount: ");
			accNo = keyboard.readInt("Enter recipient account no: ");
			
			if(amount > account.getRunningBalance()) {
				System.out.println("Insufficient balance!! ");
				LOG.error(String.valueOf(System.currentTimeMillis()) + ": Insufficient balance to perform transaction");
			} else if (amount < 0) {
				System.out.println("Invalid amount!! ");
				invalid = false;
				
			} else {
				recipientAccount = accountRepo.getAccount(accNo);
				
				account.setRunningBalance(account.getRunningBalance()-amount);
				recipientAccount.setRunningBalance(recipientAccount.getRunningBalance() + amount);
				
				System.out.println("Send successful!! ");
				invalid = true;
				// senders transaction
				newTransaction.setAccountId(account.getAccId());
				newTransaction.setAmount(amount);
				newTransaction.setTransactionType("Send");
				newTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				newTransaction.setTransactionId(generateRandomString(8));
				
				// Recipient transaction
				recevTransact.setAccountId(recipientAccount.getAccId());
				recevTransact.setAmount(amount);
				recevTransact.setTransactionType("Receive");
				recevTransact.setTransactionDate(new Timestamp(System.currentTimeMillis()));
				recevTransact.setTransactionId(generateRandomString(8));
				
				
				
				//update transaction in the database
				transactionDataAccess.setTransaction(newTransaction);
				//update the account in the database
				accountRepo.updateAccount(account);
				
				// for recipient
				//update transaction in the database
				transactionDataAccess.setTransaction(recevTransact);
				//update the account in the database
				accountRepo.updateAccount(recipientAccount);
			}
		} while(!invalid);
		
		System.out.println("******************** Send Money **************** ");
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Money sent");
	}

	// query database for received money
	// accept money transfer
	private void acceptMoneyTransfer() {
		LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Accept Money");
		System.out.println("******************** Accept Money Transfer **************** ");
		int amount, response = 0;
		boolean invalid = false;
		Transaction newTransaction = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		transactions = transactionDataAccess.getReceivedTransactions(account.getAccId());
		if(transactions.size() == 0) {
			System.out.println("Sorry you have not received any cash! ");
		}else if(transactions.size() >= 1) {
			System.out.println("You have the following transactions! ");
			for(Transaction trans: transactions) {
				if(!trans.isApproved()) {
					System.out.println(trans.getAmount() +  " received on " + String.valueOf(trans.getTransactionDate()) + " from " + trans.getAccountId());
					response = keyboard.readInt("Enter 1 to Accept or 0 to decline: ");
					
					if(response == 1) {
						trans.setApproved(true);
						
						transactionDataAccess.updateTransaction(trans);
						
					}
				}else {
					System.out.println("You have approved all transactions");
				}
			}
		}
		
		
		System.out.println("******************** Accept Money Transfer **************** ");
	}

	// view all transactions
	private void viewAllTransactions() {
		LOG.debug(String.valueOf(System.currentTimeMillis()) + ": List transactions");
		System.out.println("******************** View All Transactions **************** ");
		for(Transaction transaction : account.getTransactions()) {
			System.out.println(transaction.getTransactionId() + " | " + transaction.getTransactionType() + " | " + transaction.getAmount() + " | " + transaction.getTransactionDate());
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
		Accounts account = new Accounts();
		System.out.println("******************** Open Account **************** ");
		//SignUp.addCustomer();
		String balance = keyboard.readString("How much do u wish to deposit today?\nMin of $50: ");
		double runningBalance=0.0;
		
		boolean isfloat = true, validBal= true;
		while(isfloat && validBal) {
			try {
				runningBalance = Double.parseDouble(balance);
				isfloat = false;
				if(runningBalance >=50.0) {
					validBal = true;
				}else {
					validBal = false;
					System.out.println("Deposit at least $50!");
				}
			}catch(NumberFormatException e) {
				System.out.println("Input is not a valid balance!");
				balance = keyboard.readString("How much do u wish to deposit today?\nMin of $50: ");
			}
		}
		account = SignUp.createAccount(customer, runningBalance, customer.getUserId());
		
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
			if(!customer.isVerified()) {
				System.out.println("******************** Approve Account **************** ");
				System.out.println("Customer Id: " + customer.getUserId());
				System.out.println("Customer Name: " + customer.getFirstName() + " " + customer.getLastName());
				System.out.println("Address: " + customer.getAddress() + " " + customer.getCity() + " " + String.valueOf(customer.getZipCode()) + " " + customer.getState());
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
