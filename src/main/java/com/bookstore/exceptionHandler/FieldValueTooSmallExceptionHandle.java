package com.bookstore.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bookstore.exception.FieldValueTooSmallException;

@ControllerAdvice
public class FieldValueTooSmallExceptionHandle {
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public String handleInvalidInputException(FieldValueTooSmallException exception) {
		return exception.getMessage();
	}
	
}