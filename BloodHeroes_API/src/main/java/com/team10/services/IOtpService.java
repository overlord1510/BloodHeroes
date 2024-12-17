package com.team10.services;

import com.team10.customExceptions.OtpExpiredException;
import com.team10.customExceptions.OtpNotFoundException;

public interface IOtpService {

	public String verifyOTP(int opt) throws OtpNotFoundException, OtpExpiredException;

}
