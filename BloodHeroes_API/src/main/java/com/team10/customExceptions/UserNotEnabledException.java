package com.team10.customExceptions;

import org.springframework.security.core.AuthenticationException;

public class UserNotEnabledException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotEnabledException(String message) {
		super(message);
	}

}
