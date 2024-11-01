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
	public void registerUser(UserDTO userDTO)
			throws IllegalArgumentException, OptimisticLockingFailureException, DataIntegrityViolationException {

		//@formatter:off	
				userRepository.save(User.builder()
						.name(userDTO.getName())
						.email(userDTO.getEmail())
						.password(userDTO.getPassword())
						.contacts(userDTO.getContacts())
						.build());
		//@formatter:on
	}

	@Override
	public User getUserByEmail(String email) throws IllegalArgumentException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email" + email));
	}

	@Transactional
	@Override
	public void acceptUser(Long id) {
		userRepository.acceptHospital(id);
	}
}
