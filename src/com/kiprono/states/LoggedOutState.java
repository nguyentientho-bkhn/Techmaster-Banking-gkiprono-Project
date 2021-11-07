package com.kiprono.states;

public class LoggedOutState implements UserState {

	private LoggedOutState instance = new LoggedOutState();
	@Override
	public void updateState(Context context) {
		// TODO Auto-generated method stub
		context.setState(this);		
	}

	public LoggedOutState() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unused")
	private LoggedOutState getLoggedOutInstance() {
		return this.instance;
	}

	@Override
	public void gotoState() {
		// TODO Auto-generated method stub
		
	}
}
