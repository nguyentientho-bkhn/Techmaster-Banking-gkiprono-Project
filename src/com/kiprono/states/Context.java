package com.kiprono.states;
import com.kiprono.models.*;

public class Context {
	private UserState state;
	private Customers customer;

	public Context() {
		// TODO Auto-generated constructor stub
		state = null;
		customer = null;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}
	
	public void update() {
		state.updateState(this);
	}
}
