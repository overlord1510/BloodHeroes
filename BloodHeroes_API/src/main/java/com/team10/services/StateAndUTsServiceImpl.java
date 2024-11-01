package com.team10.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.team10.entity.StateAndUTs;
import com.team10.repository.StateAndUTsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StateAndUTsServiceImpl implements IStateAndUTsService {

	private final StateAndUTsRepository stateAndUTsRepository;

	@Override
	public List<String> getStateAndUTsList() {
		List<String> stateList = stateAndUTsRepository.findAll().stream().map(StateAndUTs::getName).toList();
		return stateList;
	}

	@Override
	public List<String> getDistrictList(String stateName) throws NoSuchElementException {
		StateAndUTs stateAndUTs = stateAndUTsRepository.findByName(stateName).orElseThrow();
		return stateAndUTs.getDistricts();
	}

	@Override
	public StateAndUTs getStateAndUtsById(Long id) throws IllegalArgumentException {
		return stateAndUTsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("State not found with id : " + id));
	}

}
