package com.kiprono.controllers;
import com.kiprono.models.*;
import com.kiprono.utils.*;

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
	
	// handles new customer, returns account number;
	public static int addCustomer() {
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
		boolean isfloat = true;
		while(isfloat) {
			try {
				runningBalance = Double.parseDouble(balance);
				isfloat = false;
			}catch(NumberFormatException e) {
				System.out.println("Input is not a valid balance!");
				balance = keyboard.readString("How much do u wish to deposit today?\nMin of $50: ");
			}
		}
		
		// set the details
		account = createAccount(newCustomer, runningBalance);
		newCustomer.setCustomerAccount(account);
		
		// encrypt password <account number for now>
		
		plainTextPass = String.valueOf(account.getAccountNumber());
		try {
			encryptedPassword = encoderDecoder.encrypt(plainTextPass);
			newCustomer.setPasswd(encryptedPassword);
			// update this record in database
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		newCustomer.setFirstName(firstName);
		newCustomer.setMiddleInitial(middleInitial);
		newCustomer.setLastName(lastName);
		
		newCustomer.setPhoneNumber(phoneNumber);
		newCustomer.setAddress(address);
		newCustomer.setCity(city);
		newCustomer.setState(state);
		newCustomer.setZipCode(zip_code);
		
		return newCustomer.getCustomerAccount().getAccountNumber();
	}
	
	
	// creates and return the account details
	private static Accounts createAccount(Customers newCustomer, double runningBalance) {
		Accounts account = new Accounts();
		int accountNumber;
		accountNumber = generateAccountNumber();
		
		account.setAccountNumber(accountNumber);
		account.setRunningBalance(runningBalance);
		account.setAccountHolder(newCustomer);
		
		return account;
	}
	
	// create and returns valid account number
	private static int generateAccountNumber() {
		int acc = 0;
		int min = 100000000;
		int max = 999999999;
		
		// future check if generated random number is already in use
		acc = (int)Math.floor(Math.random()*(max-min+1)+min);
		
		return acc;
	}
}
