package com.team10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "hospital_services")
@NoArgsConstructor
@AllArgsConstructor
public class HospitalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    @Column(name = "emergency_services", nullable = false)
    private Boolean emergencyServices;

    @Column(name = "icu_available", nullable = false)
    private Boolean icuAvailable;

    @Column(name = "ambulance_services", nullable = false)
    private Boolean ambulanceServices;

    @Column(name = "laboratory_available", nullable = false)
    private Boolean laboratoryAvailable;

    @Column(name = "pharmacy_available", nullable = false)
    private Boolean pharmacyAvailable;

    @Column(name = "blood_bank_available", nullable = false)
    private Boolean bloodBankAvailable;

    @Column(name = "number_of_operating_theaters", nullable = false)
    private Integer numberOfOperatingTheaters;

}
