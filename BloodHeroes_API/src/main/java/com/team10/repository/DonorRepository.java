package com.team10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team10.entity.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {

	@Query("SELECT d FROM Donor d WHERE d.user.email = :email")
	Optional<Donor> findByUserEmail(@Param("email") String email);

}
