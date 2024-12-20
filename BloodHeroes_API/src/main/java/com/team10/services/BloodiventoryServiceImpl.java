package com.team10.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.team10.customExceptions.UserNotFoundException;
import com.team10.dto.BloodDonationRequest;
import com.team10.entity.BloodInventory;
import com.team10.entity.Donor;
import com.team10.entity.Hospital;
import com.team10.repository.BloodInventoryRepository;
import com.team10.repository.DonorRepository;
import com.team10.repository.HospitalRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BloodiventoryServiceImpl implements IBloodInventoryService {

	private final DonorRepository donourRepository;
	private final HospitalRepository hospitalRepository;
	private final BloodInventoryRepository bloodInventoryRepository;

	@Value("${blood.expiration_time}")
	private long bloodExpirationTime;

	@Override
	@Transactional
	public void addBlood(BloodDonationRequest bloodDonationRequest) throws UserNotFoundException {
		Donor donor = donourRepository.findByUserEmail(bloodDonationRequest.getDonourEmail())
				.orElseThrow(() -> new UserNotFoundException(
						"User not found with email : " + bloodDonationRequest.getDonourEmail()));

		Hospital hospital = hospitalRepository.findByUserEmail(bloodDonationRequest.getHospitalEmail())
				.orElseThrow(() -> new UserNotFoundException(
						"User not found with email : " + bloodDonationRequest.getHospitalEmail()));

		// @formatter:off
			bloodInventoryRepository.save(BloodInventory.builder()
						.hospital(hospital)
						.donour(donor)
						.bloodType(bloodDonationRequest.getBloodType())
						.amount(bloodDonationRequest.getAmount())
						.donationDate(bloodDonationRequest.getDonationDate())
						.expirationDate(new Date(bloodDonationRequest.getDonationDate().getTime()+bloodExpirationTime))
						.isAccepted(false)
					.build());
		// @formatter:on

	}

}
