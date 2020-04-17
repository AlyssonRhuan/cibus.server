package com.arcs.cibus.server.service.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}
	
	public EmailAlreadyRegisteredException(String message, Throwable cause) {
		super(message, cause);
	}

}
