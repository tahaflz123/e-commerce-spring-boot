package com.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class OrderException extends RuntimeException{

	public OrderException(String message) {
		super(message);
	}

	
}
