package com.team10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team10.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
}
