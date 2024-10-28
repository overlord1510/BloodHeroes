package com.team10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team10.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	@Query("SELECT h FROM Hospital h WHERE h.user.isActivated = false")
	List<Hospital> findByUserIsActicatedFalse();
	
}
