package com.team10.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team10.services.IHospitalService;
import com.team10.services.IUserService;
import com.team10.vo.HospitalVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	private final IHospitalService hospitalService;
	private final IUserService userService;

	@GetMapping
	ResponseEntity<?> getAccountOpeningRequest() {
		List<HospitalVO> toBeActivated = hospitalService.getHospitalsToBeActivated();
		if (toBeActivated.size() > 0) {
			return new ResponseEntity<List<HospitalVO>>(toBeActivated, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/accept/{id}")
	ResponseEntity<?> acceptHospitalRegistration(@PathVariable("id") Long id) {
		log.info("ACCEPTING Hospital with id : " + id);
		userService.acceptUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
