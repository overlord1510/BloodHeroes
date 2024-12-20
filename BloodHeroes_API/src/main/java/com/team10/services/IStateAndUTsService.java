package com.team10.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.team10.entity.StateAndUTs;

public interface IStateAndUTsService {

	public List<String> getStateAndUTsList();

	public List<String> getDistrictList(String stateName) throws NoSuchElementException;

	public StateAndUTs getStateAndUtsById(Long id) throws IllegalArgumentException;

}
