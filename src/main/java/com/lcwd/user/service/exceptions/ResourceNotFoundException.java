package com.lcwd.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 4050592099196413232L;

	public ResourceNotFoundException() {
		super("Resource not found on server !!");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
