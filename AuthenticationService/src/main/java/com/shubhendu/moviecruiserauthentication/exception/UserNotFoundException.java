package com.shubhendu.moviecruiserauthentication.exception;

public class UserNotFoundException extends Exception{
	
	private String message; 
	
	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}


}
