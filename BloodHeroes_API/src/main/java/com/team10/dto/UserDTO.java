package com.team10.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
	Long id;
	String name;
	String email;
	String password;
	String contacts;
}
