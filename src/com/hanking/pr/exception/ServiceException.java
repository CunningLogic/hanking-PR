package com.hanking.pr.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private String message = null;

	public ServiceException(String message) {
		this.message = message;
	}

	public ServiceException() {

	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}