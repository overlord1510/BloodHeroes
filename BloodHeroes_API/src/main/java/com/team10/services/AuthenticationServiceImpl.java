package com.team10.services;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.team10.config.JwtUtils;
import com.team10.customExceptions.UserNotEnabledException;
import com.team10.dto.AuthenticationRequest;
import com.team10.dto.AuthenticationResponse;
import com.team10.entity.RefreshToken;
import com.team10.entity.User;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final IUserService iUserService;
	private final JwtUtils jwtUtils;
	private final CookieServiceImpl cookieServiceImpl;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;

	@Override
	public AuthenticationResponse authenticateAndCreateCookie(AuthenticationRequest request,
			HttpServletResponse response) throws UserNotEnabledException {
		log.info("Inside Auth Service: {}", request.getEmail());

		// Retrieve user by email first
		User userByEmail = iUserService.getUserByEmail(request.getEmail());

		// Check if user exists and is enabled
		if (userByEmail == null || !userByEmail.isActivated()) {
			log.warn("User account not enabled for email: {}", request.getEmail());
			throw new UserNotEnabledException("User account is not enabled");
		}

		// Proceed with authentication
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		log.info("Authentication success for email: {}", request.getEmail());

		if (authentication.isAuthenticated()) {
			String token = jwtUtils.generateToken(userByEmail);
			log.info("Generated token: {}", token);

			String refreshToken = UUID.randomUUID().toString();

			RefreshToken refreshTokenByUser = refreshTokenServiceImpl.getRefreshTokenByUser(userByEmail);

			if (refreshTokenByUser != null) {
				refreshTokenServiceImpl.deleteRefreshToken(userByEmail);
			}
			refreshTokenServiceImpl.saveRefreshToken(refreshToken, userByEmail);
			cookieServiceImpl.createCookie(refreshToken, response);

			return AuthenticationResponse.builder().email(request.getEmail()).token(token)
					.role(userByEmail.getRole().toString()).build();
		}

		return null;
	}

}
