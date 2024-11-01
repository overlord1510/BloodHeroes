package com.team10.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.team10.customExceptions.UserNotEnabledException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		if (authException instanceof UserNotEnabledException) {
			log.warn("Account Not enabled forbidden will be returned");
			log.error(authException.toString());
			response.setStatus(HttpStatus.FORBIDDEN.value());
		} else {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
		}
	}
}
