package com.arcs.cibus.server.service.exceptions;

public class OrderNotClosedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotClosedException(String message) {
		super(message);
	}

	public OrderNotClosedException(String message, Throwable cause) {
		super(message, cause);
	}

}
