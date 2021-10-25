package com.kiprono.controllers;
import com.kiprono.models.*;
import com.kiprono.utils.*;


public class Login {
	private static Customers customer;
	// personal details
	private String firstName;
	private String middleInitial;
	private String lastName;
	
	// contacts
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	private int zipCode;
	
	// login
	private static String userId;
	private static String passwd;
	private static String encryptedPass, plaintxtPass;
	
	private static Keyboard keyboard = Keyboard.getKeyboard();
	private static EncryptDecrypt encoderDecoder = EncryptDecrypt.getEncryptor();
	
	
	
	Login(){
		customer = new Customers();
	}
	
	// get user id and user name
	
	public static boolean handleLogin() {
		boolean isint= true, authorised = true, loggedIn = true;
		int uid = 0;
		System.out.println("\nEnter Userid and password");
		userId = keyboard.readString("UserId:\t");
		
		while(isint) {
			try {
				uid = Integer.parseInt(userId);
				isint = false; // sets to false if above line executes well
			} catch(NumberFormatException e) {
				System.out.println("Input is not a valid integer");
				userId = keyboard.readString("UserId:\t");
			}
			
		}
		
		passwd = keyboard.readString("Password:\t");
		
		if(authenticateCustomers(uid, passwd)) {
			
		}else {
			System.out.println("This feature is coming\nTaking you back to main");
			authorised = false;
			
		}
		
		return authorised;
	}
	
	// checks details if they match with the records
	// if password same as account number, then user is first time login, prompt change of password
	private static boolean authenticateCustomers(int userId,String password) {
		boolean authenticated = false;
		boolean firstTimeUser = false;
		int passw = 0;
		try {
			passw = Integer.valueOf(password);
			firstTimeUser = true;
		}catch(NumberFormatException e) {
			// input is a string
			
		}	
		/*
		 * query to fetch all  WHERE userId = userId
		 * update customer variable
		 * 
		 * */
		
		// check for 1st time login
		// customer.getCustomerAccount().getAccountNumber()
//		passw == customer.getCustomerAccount().getAccountNumber()
		if(firstTimeUser) {
			firstTimeUser = changePassword();
		} 
		else {
			try {
				encryptedPass = customer.getPasswd();
				
				if(encryptedPass == encoderDecoder.encrypt(password)) {
					authenticated = true;
				}
			} catch(NullPointerException s) {
				
				return authenticated;
			} catch (Exception e) {
				//e.printStackTrace();
				// this feature is still developing, our customer is null at the moment
			}
		}
		
		return authenticated;
	}
	
	// change password
	private static boolean changePassword() {
		boolean changed = false;
		String newPasswd, confirmPasswd;
		System.out.println("*****************\nChange your default password\n");
		newPasswd = keyboard.readString("Enter new Password: ");
		confirmPasswd = keyboard.readString("Confirm Password: ");
		
		// check if they match
		while(newPasswd != confirmPasswd) {
			System.out.println("******* Passwords dont match ******");
			
			newPasswd = keyboard.readString("Enter new Password: ");
			confirmPasswd = keyboard.readString("Confirm Password: ");
		}
		
		// update password and leave
		if(newPasswd == confirmPasswd) {
			try {
				encryptedPass = encoderDecoder.encrypt(confirmPasswd);
				customer.setPasswd(encryptedPass);
			}catch(Exception e){
				e.printStackTrace();
			}
			changed = true;
		}
		
		return changed;
	}
	
	
	
}
