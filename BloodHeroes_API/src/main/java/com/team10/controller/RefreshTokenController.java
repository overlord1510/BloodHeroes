package com.team10.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team10.config.JwtUtils;
import com.team10.dto.AuthenticationResponse;
import com.team10.entity.RefreshToken;
import com.team10.services.ICookieService;
import com.team10.services.IRefreshTokenService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/refresh")
@RequiredArgsConstructor
public class RefreshTokenController {

	private final IRefreshTokenService refreshTokenService;
	private final ICookieService cookieService;
	private final JwtUtils jwtUtils;

	@GetMapping
	ResponseEntity<?> refreshToken(@CookieValue(defaultValue = "rToken") String rToken, HttpServletResponse response,boolean rememberMe) {
		RefreshToken refreshTokenByToken = refreshTokenService.getRefreshTokenByToken(rToken);
		if (refreshTokenByToken != null
				&& refreshTokenByToken.getExpiration().after(new Date(System.currentTimeMillis()))) {
			refreshTokenService.deleteRefreshToken(refreshTokenByToken.getUser());
			String newToken = UUID.randomUUID().toString();
			refreshTokenService.saveRefreshToken(newToken, refreshTokenByToken.getUser());
			cookieService.createCookie(newToken, response,rememberMe);
			String token = jwtUtils.generateToken(refreshTokenByToken.getUser());
			//@formatter:off
			return new ResponseEntity<AuthenticationResponse>(AuthenticationResponse.builder()
					.token(token)
					.email(refreshTokenByToken.getUser().getEmail())
					.role(refreshTokenByToken.getUser().getRole().toString())
					.build(),HttpStatus.OK);
			//@formatter:on
		}
		cookieService.deleteCookie(response);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
