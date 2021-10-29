package com.kiprono.states;
import com.kiprono.controllers.*;
import com.kiprono.models.*;
import com.kiprono.utils.*;

/*
 * while here, user can deposit, withdraw, perform account-account transfer, accept transfer
 * or change personal details
 * employees can do that too
 * */

@SuppressWarnings("unused")
public class LoggedInState implements UserState {
	private LoggedInState instance = new LoggedInState();
	private LoggedInState() { 
		// TODO Auto-generated constructor stub
	}
	
	public LoggedInState getLoggedInInstance() {
		return this.instance;
	}

	@Override
	public void updateState(Context context) {
		// TODO Auto-generated method stub
		context.setState(this);
	}
	

}
