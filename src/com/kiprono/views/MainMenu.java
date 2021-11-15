package com.kiprono.views;
import com.kiprono.controllers.Login;
import com.kiprono.controllers.SignUp;
import com.kiprono.models.Customers;
import com.kiprono.states.Context;
import com.kiprono.states.LoggedInState;
import com.kiprono.utils.Keyboard;

@SuppressWarnings("unused")
public class MainMenu {
	
	private static MainMenu menu = new MainMenu();
	public static boolean loggedIn = false;
	private static Customers customer = null;
	private static Context ctxt =  new Context();
	private static LoggedInState state = new LoggedInState();
	
	public static MainMenu getMenu() {
		return menu;
	}


	public static int Home() {
		Keyboard keyboard = Keyboard.getKeyboard();
		int choice = 0;
		
		System.out.println("\n****************************************************************");
		System.out.println("                 SELORE & S-GLOBAL BANK");
		System.out.println("Welcome!!\nSelect an option below");
		System.out.println("1. Login to your bank.");
		System.out.println("2. Signup for bank account.");
		System.out.println("3. Help.");
		System.out.println("4. Exit");
		
		choice = keyboard.readInt("Enter 1-4: ");
		while(choice < 1 || choice > 4) {
			System.out.print("Invalid choice!! ");
			choice = keyboard.readInt("Enter 1-4: ");
		}
		
		return choice;
	}
	
	// do this
	public static void ContextMenu() {
		int choice;
		String uname=null;
		Login login =  new Login();
		int acc=0;
		do {
			choice = Home();
			loggedIn = false;
			switch (choice) {
				case 1:
					// login page
					loggedIn = login.handleLogin();
					if(loggedIn) {
						// logged in state
						ctxt.setCustomer(customer);
						state.gotoState();
					}
					break;
				case 2:
					// sign up
					customer = SignUp.addCustomer();
					System.out.println("\n*****\nCongrats, your acc number is: " + String.valueOf(customer.getAccountNumber())+ "\n*****");
					System.out.println("Your username is: " + customer.getUserName() + " and password is your account number");
					System.out.println("You can now log in to the bank");
					break;
				case 3:
					System.out.println("Help is on the way");
					// help menu
					break;
				case 4:
					// exit
					break;
				default:
					System.out.println("Error");
			}
			
			
		} while (choice != 4);
		System.out.println("\n****************************************************************");
		System.out.println("   thanks for using SELORE & S-GLOBAL BANK, have a great day");
	}
	
}
