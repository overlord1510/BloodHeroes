package com.team10.services;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.team10.customExceptions.UserNotFoundException;
import com.team10.entity.OTP;
import com.team10.entity.User;
import com.team10.repository.OtpRepository;
import com.team10.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {

	private final JavaMailSender javaMailSender;

	private final UserRepository userRepository;
	private final OtpRepository otpRepository;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${otp.expiration_time}")
	private String otpExpirationTime;

	@Override
	public void sendOTP(String email) throws UserNotFoundException, MailException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with email : " + email));

		int otp = new Random().ints(1, 100000, 999999).sum();
		
		// @formatter:off
		otpRepository.save(OTP.builder()
					.user(user)
					.otpCode(otp)
					.expirationTime(new Date(System.currentTimeMillis()+Integer.parseInt(otpExpirationTime)))
				.build());
		// @formatter:on
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(sender);
		mailMessage.setTo(email);
		mailMessage.setText("Your One Time Password is :: " + otp + "\nValid for 15 minutes");
		mailMessage.setSubject("One Time Password for Blood Heroes password recovery");
		javaMailSender.send(mailMessage);
	}
}
