package com.shubhendu.moviecruiserserverapplication.exception;

@SuppressWarnings("serial")
public class MovieNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private String message;

	public MovieNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
