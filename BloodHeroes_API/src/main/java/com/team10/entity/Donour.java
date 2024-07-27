package com.team10.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "donour")
public class Donour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donour_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "pincode")
	private Integer pinCode;
	
	@Column(name = "address_line_1")
	private String addressLine1;
	
	@Column(name = "address_line_2")
	private String addressLine2;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateAndUTs stateAndUTs;
	
	@Column(name = "district")
	private String district;

}