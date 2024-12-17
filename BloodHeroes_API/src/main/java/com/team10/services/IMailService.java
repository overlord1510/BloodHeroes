package com.team10.services;

import org.springframework.mail.MailException;

import com.team10.customExceptions.UserNotFoundException;

public interface IMailService {

	public void sendOTP(String email) throws UserNotFoundException,MailException;
	
}
