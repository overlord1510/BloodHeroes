package com.team10.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonourDTO {
	private String name;
	private String email;
	private String password;
	private String contacts;
	private String gender;
	private Integer pinCode;
	private String addressLine1;
	private String addressLine2;
	private Long stateAndUTsID;
	private String district;
}
