package com.luv2code.springdemo.rest.controller;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String theMessage) {
		super(theMessage);
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
	}
}
