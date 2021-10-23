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
	
	// contacts
	private static String phoneNumber;
	private static String address;
	private static String city;
	private static String state;
	private static String zipCode;
	
	public static Customers createAccount() {
		boolean isint = true;
		Customers newCustomer = new Customers();
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
				zipCode = keyboard.readString("UserId:\t");
			}
		}
		
		newCustomer.setFirstName(firstName);
		newCustomer.setMiddleInitial(middleInitial);
		newCustomer.setLastName(lastName);
		
		newCustomer.setPhoneNumber(phoneNumber);
		newCustomer.setAddress(address);
		newCustomer.setCity(city);
		newCustomer.setState(state);
		newCustomer.setZipCode(zip_code);
		
		return newCustomer;
	}

}
