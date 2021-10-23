package com.kiprono.controllers;
import com.kiprono.utils.*;

public class MainMenu {
	
	private static MainMenu menu = new MainMenu();
	private static boolean loggedIn = false;
	
	public static MainMenu getMenu() {
		return menu;
	}


	public static int Home() {
		Keyboard keyboard = Keyboard.getKeyboard();
		int choice = 0;
		
		
		do {
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
		} while (choice != 4);
		
	}
	
	public static void ContextMenu() {
		int choice = Home();
		switch(choice){
			case 1 :
				// login page
				loggedIn = Login.handleLogin();
				loggedIn = false;
				choice = Home();
				 break;
			case 2:
				// signup
				SignUp.createAccount();
				break;
			case 3 :
				// help menu
				break;
			case 4:
				// exit
				break;
			default:
				System.out.println("Error");
		}
	}
	
}
