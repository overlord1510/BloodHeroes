package com.team10.services;

import com.team10.customExceptions.UserNotEnabledException;
import com.team10.customExceptions.UserNotFoundException;
import com.team10.dto.AuthenticationRequest;
import com.team10.dto.AuthenticationResponse;
import com.team10.dto.PasswordChangeDTO;

import jakarta.servlet.http.HttpServletResponse;

public interface IAuthenticationService {
	public AuthenticationResponse authenticateAndCreateCookie(AuthenticationRequest request,
			HttpServletResponse response) throws UserNotEnabledException;
	
	public void changePassword(PasswordChangeDTO changeDTO)throws UserNotFoundException ;
}
