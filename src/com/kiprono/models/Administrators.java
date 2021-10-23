package com.kiprono.models;

public class Administrators extends Users{
	// classifier
	private String role;
	
	// administrators can or cannot have an account too
	
	Accounts userAccount;
	
	// getters and setters
	public void setAccount(Accounts acc) {this.userAccount = acc;}
	
	public Accounts getAccount(Accounts acc) {return this.userAccount;}
	

	public Accounts getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Accounts userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String getCity() {
		// TODO Auto-generated method stub
		return super.getCity();
	}

	@Override
	public void setCity(String city) {
		// TODO Auto-generated method stub
		super.setCity(city);
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return super.getState();
	}

	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		super.setState(state);
	}

	@Override
	public int getZipCode() {
		// TODO Auto-generated method stub
		return super.getZipCode();
	}

	@Override
	public void setZipCode(int zipCode) {
		// TODO Auto-generated method stub
		super.setZipCode(zipCode);
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		super.setFirstName(firstName);
	}

	@Override
	public String getMiddleInitial() {
		// TODO Auto-generated method stub
		return super.getMiddleInitial();
	}

	@Override
	public void setMiddleInitial(String middleInitial) {
		// TODO Auto-generated method stub
		super.setMiddleInitial(middleInitial);
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		super.setLastName(lastName);
	}

	@Override
	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return super.getPhoneNumber();
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		super.setPhoneNumber(phoneNumber);
	}

	@Override
	public String getAddress() {
		return super.getAddress();
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		super.setAddress(address);
	}

	@Override
	public int getUserId() {
		// TODO Auto-generated method stub
		return super.getUserId();
	}

	@Override
	public void setUserId(int userId) {
		// TODO Auto-generated method stub
		super.setUserId(userId);
	}

	public String getRole() {
		return role;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return super.getUserName();
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		super.setUserName(userName);
	}

	@Override
	public String getPasswd() {
		// TODO Auto-generated method stub
		return super.getPasswd();
	}

	@Override
	public void setPasswd(String passwd) {
		// TODO Auto-generated method stub
		super.setPasswd(passwd);
	}

	@Override
	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return super.isAdmin();
	}

	@Override
	public void setAdmin(boolean isAdmin) {
		// TODO Auto-generated method stub
		super.setAdmin(isAdmin);
	}

	public void setRole(String role) {
		this.role = role;
	}
}
