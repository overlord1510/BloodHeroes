package com.team10.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.team10.dto.UserDTO;
import com.team10.entity.User;
import com.team10.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepository;

	@Transactional
	@Override
	public void registerUser(UserDTO userDTO) throws IllegalArgumentException, OptimisticLockingFailureException,DataIntegrityViolationException {

		//@formatter:off	
				userRepository.save(User.builder()
						.name(userDTO.getName())
						.email(userDTO.getEmail())
						.password(userDTO.getPassword())
						.contacts(userDTO.getContacts())
						.build());
		//@formatter:on
	}
}
