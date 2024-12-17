package com.team10.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;

import com.team10.dto.DonorDTO;

public interface IDonourService {

	public void saveDonour(DonorDTO donourDTO)
			throws IllegalArgumentException, OptimisticLockingFailureException, DataIntegrityViolationException;

}
