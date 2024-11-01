package com.team10.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;

import com.team10.dto.UserDTO;
import com.team10.entity.User;

public interface IUserService {
	
	public void registerUser(UserDTO userDTO)
			throws IllegalArgumentException, OptimisticLockingFailureException, DataIntegrityViolationException;

	public User getUserByEmail(String email) throws IllegalArgumentException;
	
	public void acceptUser(Long id);
	
}
