package com.team10.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "hospital")
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hospital_id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "registration_number", nullable = false, unique = true)
	private String registrationNumber;

	@Column(name = "hospital_type", nullable = false)
	private String hospitalType;

	@Column(name = "date_of_establishment", nullable = false)
	private Date dateOfEstablishment;

	@Column(name = "accreditation")
	private String accreditation;

	@Column(nullable = false)
	private Integer bedCapacity;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateAndUTs stateAndUTs;
	
	@Column(name = "district")
	private String district;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "service_id", referencedColumnName = "service_id")
	private HospitalService hospitalService;

}
