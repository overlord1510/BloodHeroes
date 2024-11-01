package com.team10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team10.dto.AuthenticationRequest;
import com.team10.dto.AuthenticationResponse;
import com.team10.services.IAuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	private final IAuthenticationService iAuthenticationService;

	@PostMapping("/authenticate")
	ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response){
		System.out.println("This is "+authenticationRequest.getEmail()+authenticationRequest.getPassword());
		AuthenticationResponse auth = iAuthenticationService.authenticateAndCreateCookie(authenticationRequest, response);
		if(auth!=null) {
			return new ResponseEntity<AuthenticationResponse>(auth,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
}
