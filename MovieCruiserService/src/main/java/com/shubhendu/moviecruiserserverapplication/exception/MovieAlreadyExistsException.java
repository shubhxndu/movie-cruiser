package com.shubhendu.moviecruiserserverapplication.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception{
	/**
	 * 
	 */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MovieAlreadyExistsException(String message) {
		super();
		this.message = message;
	} 
	
	
}
