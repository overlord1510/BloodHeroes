package com.team10.services;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team10.config.JwtUtils;
import com.team10.customExceptions.UserNotEnabledException;
import com.team10.customExceptions.UserNotFoundException;
import com.team10.dto.AuthenticationRequest;
import com.team10.dto.AuthenticationResponse;
import com.team10.dto.PasswordChangeDTO;
import com.team10.entity.RefreshToken;
import com.team10.entity.User;
import com.team10.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
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
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
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
				log.info("Refresh Token is present");
				refreshTokenServiceImpl.deleteRefreshToken(userByEmail);
			}
			refreshTokenServiceImpl.saveRefreshToken(refreshToken, userByEmail);
			cookieServiceImpl.createCookie(refreshToken, response, request.isRememberMe());

			return AuthenticationResponse.builder().email(request.getEmail()).token(token)
					.role(userByEmail.getRole().toString()).build();
		}

		return null;
	}

	@Override
	@Transactional
	public void changePassword(PasswordChangeDTO changeDTO) throws UserNotFoundException {
		User user = userRepository.findByEmail(changeDTO.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found with email : " + changeDTO.getEmail()));
		user.setPassword(passwordEncoder.encode(changeDTO.getPassword()));
		userRepository.save(user);
	}

}
