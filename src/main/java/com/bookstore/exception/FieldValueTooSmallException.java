package com.bookstore.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class FieldValueTooSmallException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9117917137333312569L;
	
	private String message;
	
	public FieldValueTooSmallException(){
		
	}	
	
	public FieldValueTooSmallException(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}