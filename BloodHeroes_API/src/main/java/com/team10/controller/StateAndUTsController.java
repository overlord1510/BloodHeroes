package com.team10.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team10.services.IStateAndUTsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/state-and-uts")
@RequiredArgsConstructor
public class StateAndUTsController {

	private final IStateAndUTsService stateAndUTsService;

	@GetMapping
	ResponseEntity<?> getStateAndUTsName() {
		List<String> stateAndUTsList = stateAndUTsService.getStateAndUTsList();
		if (stateAndUTsList.size() > 0) {
			return new ResponseEntity<List<String>>(stateAndUTsList, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{name}")
	ResponseEntity<?> getDistrictList(@PathVariable String name) {
		try {
			List<String> districtList = stateAndUTsService.getDistrictList(name);
			return new ResponseEntity<List<String>>(districtList,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
