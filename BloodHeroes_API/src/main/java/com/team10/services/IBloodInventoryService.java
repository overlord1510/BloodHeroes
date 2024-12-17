package com.team10.services;

import com.team10.customExceptions.UserNotFoundException;
import com.team10.dto.BloodDonationRequest;

public interface IBloodInventoryService {

	public void addBlood(BloodDonationRequest bloodDonationRequest) throws UserNotFoundException;
	
}
