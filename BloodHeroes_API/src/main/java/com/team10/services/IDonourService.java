package com.team10.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;

import com.team10.dto.DonourDTO;

public interface IDonourService {

	public void saveDonour(DonourDTO donourDTO)
			throws IllegalArgumentException, OptimisticLockingFailureException, DataIntegrityViolationException;

}
