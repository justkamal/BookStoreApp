package com.bookstore.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bookstore.exception.PostNotFoundException;

@ControllerAdvice
public class PostExceptionHandler {
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public String handlePostNotFoundException(PostNotFoundException exception) {
		return exception.getMessage();
	}
	
}