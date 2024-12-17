package com.team10.customExceptions;

public class OtpNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public OtpNotFoundException(String message) {
		super(message);
	}

}
