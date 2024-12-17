package com.team10.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name="blood_inventory")
public class BloodInventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "donour_id")
	private Donor donour;
	
	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name="donation_date")
	private Date donationDate;
	
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@Column(name = "isAccepted", nullable = false)
	private boolean isAccepted;
	
}
