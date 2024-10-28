package com.team10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team10.entity.StateAndUTs;

public interface StateAndUTsRepository extends JpaRepository<StateAndUTs, Long> {

	Optional<StateAndUTs> findByName(String name);

	Optional<StateAndUTs> findById(Long id);

}
