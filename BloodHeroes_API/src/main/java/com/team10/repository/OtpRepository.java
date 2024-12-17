package com.team10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team10.entity.OTP;

public interface OtpRepository extends JpaRepository<OTP, Long> {

	Optional<OTP> findByOtpCode(int otp);

}
