package com.team10.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
	private String email;
	private String password;
	private boolean rememberMe;
}
