package com.team10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team10.customExceptions.OtpExpiredException;
import com.team10.customExceptions.OtpNotFoundException;
import com.team10.customExceptions.UserNotFoundException;
import com.team10.dto.AuthenticationRequest;
import com.team10.dto.AuthenticationResponse;
import com.team10.dto.PasswordChangeDTO;
import com.team10.services.IAuthenticationService;
import com.team10.services.MailServiceImpl;
import com.team10.services.OtpServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*", allowCredentials = "true")
@Slf4j
public class AuthenticationController {

	private final IAuthenticationService iAuthenticationService;
	private final MailServiceImpl mailServiceImpl;
	private final OtpServiceImpl otpServiceImpl;

	@PostMapping("/authenticate")
	ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse response) {
		System.out.println("This is " + authenticationRequest.getEmail() + authenticationRequest.getPassword());
		AuthenticationResponse auth = iAuthenticationService.authenticateAndCreateCookie(authenticationRequest,
				response);
		if (auth != null) {
			return new ResponseEntity<AuthenticationResponse>(auth, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/send-otp")
	public ResponseEntity<?> checkEmail(@RequestParam String email) {
		log.info(email);
		try {
			mailServiceImpl.sendOTP(email);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestParam String otp) {
		try {
			String email = otpServiceImpl.verifyOTP(Integer.parseInt(otp));
			return new ResponseEntity<String>(email, HttpStatus.OK);
		} catch (NumberFormatException | OtpNotFoundException | OtpExpiredException e) {
			if (e instanceof NumberFormatException)
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			else if (e instanceof OtpNotFoundException)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(HttpStatus.valueOf(452));
		}
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
		try {
			iAuthenticationService.changePassword(passwordChangeDTO);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
