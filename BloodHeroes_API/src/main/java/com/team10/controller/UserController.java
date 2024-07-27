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

import com.team10.dto.UserDTO;
import com.team10.services.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final IUserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
		try {
			userService.registerUser(userDTO);
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
