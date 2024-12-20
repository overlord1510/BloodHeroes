package com.team10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team10.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	@Modifying
	@Query("DELETE FROM RefreshToken r WHERE r.user.id = :id")
	void deleteByUser_id(@Param("id") Long id);

	Optional<RefreshToken> findByUser_id(Long userId);
	
	Optional<RefreshToken> findByToken(String token);

}
