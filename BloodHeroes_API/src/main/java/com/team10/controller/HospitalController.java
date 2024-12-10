package com.team10.controller;

import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team10.dto.BloodDonationRequest;
import com.team10.dto.HospitalDTO;
import com.team10.services.IHospitalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hospital")
@Slf4j
public class HospitalController {

	private final IHospitalService hospitalService;

	@PostMapping("/register")
	ResponseEntity<?> registerHospital(@RequestBody HospitalDTO hospitalDTO) {
		log.info("Inside Register Hospital");
		try {
			hospitalService.saveHospital(hospitalDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			int sqlErrorCode = ((SQLException) (e.getRootCause())).getErrorCode();
			if (sqlErrorCode == 1062)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (OptimisticLockingFailureException | IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/donate")
	ResponseEntity<?> donateBlood(@RequestBody BloodDonationRequest bloodDonationRequest){
		return new ResponseEntity<>(HttpStatus.OK);	
	}

}
