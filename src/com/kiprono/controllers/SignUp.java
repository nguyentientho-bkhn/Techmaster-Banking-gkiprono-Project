package com.kiprono.controllers;
import com.kiprono.daos.CustomersRepoImpl;
import com.kiprono.daos.CustomersRepository;
import com.kiprono.models.Accounts;
import com.kiprono.models.Customers;
import com.kiprono.utils.EncryptDecrypt;
import com.kiprono.utils.Keyboard;
import com.kiprono.utils.PasswordUtils;

public class SignUp {
	// personal details
	private static String firstName;
	private static String middleInitial;
	private static String lastName;
	
	private static int zip_code;
	
	// utility
	private static Keyboard keyboard = Keyboard.getKeyboard();
	private static EncryptDecrypt encoderDecoder = EncryptDecrypt.getEncryptor();
	
	// contacts
	private static String phoneNumber;
	private static String address;
	private static String city;
	private static String state;
	private static String zipCode;
	private static String encryptedPassword, plainTextPass;
	
	// account
	private static double runningBalance;
	private static String balance;
	private static String salt;
	
	// customers repo
	private static CustomersRepository customerRepo = new CustomersRepoImpl();
	
	// handles new customer, returns account number;
	public static Customers addCustomer() {
		
		boolean isint = true;
		Customers newCustomer = new Customers();
		Accounts account = new Accounts();
		
		// get details from user
		System.out.println("Enter your details.\nDetails marked with * are mandatory");
		firstName = keyboard.readString("Enter First Name*: ");
		middleInitial = keyboard.readString("Middle initial: ");
		lastName = keyboard.readString("Last name*: ");
		phoneNumber = keyboard.readString("Cell-phone number*: ");
		address = keyboard.readString("Living Address*: ");
		
		city = keyboard.readString("City*: ");
		state = keyboard.readString("State*: ");
		zipCode = keyboard.readString("Zipcode*: ");
		
		
		while(isint) {
			try {
				zip_code = Integer.parseInt(zipCode);
				isint = false; // sets to false if above line executes well
			} catch(NumberFormatException e) {
				System.out.println("Input is not a valid integer");
				zipCode = keyboard.readString("ZipCode*:\t");
			}
		}
		
		balance = keyboard.readString("How much do u wish to deposit today?\nMin of $50: ");
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
		
		// set the details
		
		
		// encrypt password <account number for now>
		
		plainTextPass = String.valueOf(account.getAccountNumber());
		salt = PasswordUtils.getSalt(25);
		encryptedPassword = PasswordUtils.generateSecurePassword(plainTextPass, salt);
		newCustomer.setKey(salt);
		newCustomer.setPasswd(encryptedPassword);
		
		newCustomer.setUserId(generateUserId());
		newCustomer.setFirstName(firstName);
		newCustomer.setMiddleInitial(middleInitial);
		newCustomer.setLastName(lastName);
		
		newCustomer.setPhoneNumber(phoneNumber);
		newCustomer.setAddress(address);
		newCustomer.setCity(city);
		newCustomer.setState(state);
		newCustomer.setZipCode(zip_code);

		newCustomer.setUserName(userNameGenerator());
		
		
		account = createAccount(newCustomer, runningBalance);
		newCustomer.setCustomerAccount(account);
		
		customerRepo.addCustomer(newCustomer);
		return newCustomer;
	}
	
	
	// creates and return the account details
	private static Accounts createAccount(Customers newCustomer, double runningBalance) {
		Accounts account = new Accounts();
		int accountNumber = generateAccountNumber();
		
		newCustomer.setAccountNumber(accountNumber);
		account.setAccId(generateAccountId());
		account.setAccountNumber(accountNumber);
		account.setRunningBalance(runningBalance);
		account.setAccountHolder(newCustomer);
		
		return account;
	}
	
	public static int generateAccountId() {
		int accountId = 0;
		int max = 999999;
		int min = 100000;

		accountId = (int) (Math.random() * (max - min) + min);
		return accountId;
	}


	// create and returns valid account number
	public static int generateAccountNumber() {
		int acc = 0;
		int min = 100000;
		int max = 9999999;
		
		// future check if generated random number is already in use
		acc = (int)Math.floor(Math.random()*(max-min+1)+min);
		
		return acc;
	}
	// generate user id
	public static int generateUserId() {
		int userId = 0;
		int min = 2000000;
		int max = 9999999;

		// future check if generated random number is already in use
		userId = (int)Math.floor(Math.random()*(max-min+1)+min);

		return userId;
	}

	// concatinate first name, middle initial and last name
	public static String userNameGenerator() {
		String userId = "";
		userId = firstName.substring(0, 2) + middleInitial + lastName.substring(0, 2);
		// convert userId to lowercase
		userId = userId.toLowerCase();
		
		return userId;
	}
}
