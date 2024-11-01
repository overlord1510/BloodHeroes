package com.team10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team10.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	void deleteByUser_id(Long userId);

	Optional<RefreshToken> findByUser_id(Long userId);
	
	Optional<RefreshToken> findByToken(String token);

}
