package com.luv2code.springdemo.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// Add an exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {

		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// add another exception handler ... to catch NumberFormatException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(NumberFormatException nfe) {

		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("No number found " + nfe.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// add another exception handler ... to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {

		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
