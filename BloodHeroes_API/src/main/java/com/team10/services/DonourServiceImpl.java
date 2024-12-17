package com.team10.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team10.dto.DonorDTO;
import com.team10.entity.Donor;
import com.team10.entity.ROLE;
import com.team10.entity.StateAndUTs;
import com.team10.entity.User;
import com.team10.repository.DonorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonourServiceImpl implements IDonourService {

	private final DonorRepository donourRepository;

	private final PasswordEncoder passwordEncoder;

	private final IStateAndUTsService stateAndUTsService;

	@Transactional
	@Override
	public void saveDonour(DonorDTO donourDTO)
			throws IllegalArgumentException, OptimisticLockingFailureException, DataIntegrityViolationException {

		StateAndUTs stateAndUtsById = stateAndUTsService.getStateAndUtsById(donourDTO.getStateAndUTsID());

		// @formatter:off
		donourRepository.save(
				Donor.builder()
				.user(User.builder()
						.name(donourDTO.getName())
						.email(donourDTO.getEmail())
						.password(passwordEncoder.encode(donourDTO.getPassword()))
						.contacts(donourDTO.getContacts())
						.role(ROLE.DONOUR)
						.isActivated(true)
						.build())
				.gender(donourDTO.getGender())
				.bloodType(donourDTO.getBloodType())
				.dob(donourDTO.getDob())
				.pinCode(donourDTO.getPinCode())
				.addressLine1(donourDTO.getAddressLine1())
				.addressLine2(donourDTO.getAddressLine2())
				.stateAndUTs(stateAndUtsById)
				.district(donourDTO.getDistrict())
				.build());
		// @formatter:on
	}

}
