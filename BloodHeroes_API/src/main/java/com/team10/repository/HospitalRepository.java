package com.team10.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team10.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	@Query("SELECT h FROM Hospital h WHERE h.user.isActivated = false")
	List<Hospital> findByUserIsActicatedFalse();
	
	@Query("Select h FROM Hospital h WHERE h.user.email = :email")
	Optional<Hospital> findByUserEmail(@Param("email") String email);
	
}
