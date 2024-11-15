package com.team10.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalVO {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String contacts;
	private String registrationNumber;
	private String hospitalType;
	private Date dateOfEstablishment;
	private String accreditation;
	private Integer bedCapacity;
	private Boolean emergencyServices;
	private Boolean icuAvailable;
	private Boolean ambulanceServices;
	private Boolean laboratoryAvailable;
	private Boolean pharmacyAvailable;
	private Boolean bloodBankAvailable;
	private Integer numberOfOperatingTheaters;
	private String state;
	private String district;
}
