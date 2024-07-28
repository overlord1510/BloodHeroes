package com.team10.dto;

import com.team10.entity.StateAndUTs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonourDTO {
	private Long id;
	private UserDTO userDTO;
	private String gender;
	private Integer pinCode;
	private String addressLine1;
	private String addressLine2;
	private StateAndUTs stateAndUTs;
	private String district;
}
