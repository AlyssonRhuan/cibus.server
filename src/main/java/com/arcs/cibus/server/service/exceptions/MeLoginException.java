package com.arcs.cibus.server.service.exceptions;

public class MeLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MeLoginException(String message) {
		super(message);
	}
	
	public MeLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
