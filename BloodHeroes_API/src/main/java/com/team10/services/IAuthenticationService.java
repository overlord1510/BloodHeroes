package com.team10.services;

import com.team10.customExceptions.UserNotEnabledException;
import com.team10.dto.AuthenticationRequest;
import com.team10.dto.AuthenticationResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface IAuthenticationService {
	public AuthenticationResponse authenticateAndCreateCookie(AuthenticationRequest request,
			HttpServletResponse response) throws UserNotEnabledException;
}
