package com.team10.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

	private String token;
	private String email;
	private String role;
	
}
