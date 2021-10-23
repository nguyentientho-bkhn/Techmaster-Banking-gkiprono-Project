package com.kiprono.controllers;
import com.kiprono.models.*;
import com.kiprono.utils.*;


public class Login {
	private Customers customer;
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
	
	private static Keyboard keyboard = Keyboard.getKeyboard();
	
	
	
	Login(){
		customer = new Customers();
	}
	
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
			System.out.println("This feature is coming");
			authorised = false;
			
		}
		
		return authorised;
	}
	
	private static boolean authenticateCustomers(int userId,String password) {
		boolean authenticated = false;
		
		return authenticated;
	}
	
	
	
}
