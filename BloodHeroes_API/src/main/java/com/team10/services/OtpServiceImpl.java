package com.team10.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.team10.customExceptions.OtpExpiredException;
import com.team10.customExceptions.OtpNotFoundException;
import com.team10.entity.OTP;
import com.team10.repository.OtpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements IOtpService {

	private final OtpRepository otpRepository;

	@Override
	public String verifyOTP(int otpCode) throws OtpNotFoundException, OtpExpiredException {
		OTP otp = otpRepository.findByOtpCode(otpCode)
				.orElseThrow(() -> new OtpNotFoundException("OTP Code does not exist :" + otpCode));
		if (otp.getExpirationTime().before(new Date(System.currentTimeMillis())))
			throw new OtpExpiredException("Current OTP has expired : " + otpCode);
		return otp.getUser().getEmail();
	}
}
