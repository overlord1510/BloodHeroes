package com.team10.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
	Long id;
	String name;
	String email;
	String password;
	List<String> contacts;
}