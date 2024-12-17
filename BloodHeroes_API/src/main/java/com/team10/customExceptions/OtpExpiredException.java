package com.team10.customExceptions;

public class OtpExpiredException extends Exception {

	private static final long serialVersionUID = 1L;

	public OtpExpiredException(String message) {
		super(message);
	}

}
