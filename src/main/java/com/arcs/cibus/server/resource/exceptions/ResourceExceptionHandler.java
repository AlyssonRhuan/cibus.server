package com.arcs.cibus.server.resource.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError error = StandardError.builder()
			.status(HttpStatus.NOT_FOUND.value())
			.message(e.getMessage())
			.timestamp(System.currentTimeMillis())
			.error("Object Not Found")
			.path("")
			.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> objectNotFound(Exception e){
		StandardError error = StandardError.builder()
			.status(HttpStatus.BAD_REQUEST.value())
			.message(e.getMessage())
			.timestamp(System.currentTimeMillis())
			.error("Exception")
			.path(e.getStackTrace().toString())
			.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}