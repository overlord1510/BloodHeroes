package com.team10.dto;

import java.util.Date;

import com.team10.entity.BloodType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodDonationRequest {
	
	private String hospitalEmail;
	private String donourEmail;
	private BloodType bloodType;
	private double amount;
	private Date donationDate;
	
}
