package com.kiprono.models;

public class Customers extends Users{
	
	Accounts customerAccount;

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

	public Accounts getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(Accounts customerAccount) {
		this.customerAccount = customerAccount;
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
		// TODO Auto-generated method stub
		return super.getAddress();
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		super.setAddress(address);
	}

}
