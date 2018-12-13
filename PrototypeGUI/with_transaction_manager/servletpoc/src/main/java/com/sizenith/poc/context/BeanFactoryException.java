package com.sizenith.poc.context;

public class BeanFactoryException extends RuntimeException {

	private static final long serialVersionUID = -3675028679854221762L;

	public BeanFactoryException(String message) {
		super(message);
	}
	
	public BeanFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
