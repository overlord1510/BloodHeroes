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

import com.team10.dto.DonourDTO;
import com.team10.services.IDonourService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/donour")
public class DonourController {

	private final IDonourService iDonourService;

	@PostMapping("/register")
	ResponseEntity<?> registerDonour(@RequestBody DonourDTO donourDTO) {
		try {
			iDonourService.saveDonour(donourDTO);
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

}
