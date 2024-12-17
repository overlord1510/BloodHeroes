package com.team10.dto;

import java.util.Date;

import com.team10.entity.BloodType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonorDTO {
	private String name;
	private String email;
	private String password;
	private String contacts;
	private String gender;
	private Date dob;
	private BloodType bloodType;
	private Integer pinCode;
	private String addressLine1;
	private String addressLine2;
	private Long stateAndUTsID;
	private String district;
}
