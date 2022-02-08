package com.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductException extends RuntimeException{

	public ProductException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
