package com.team10.services;

import java.util.List;
import java.util.NoSuchElementException;

public interface IStateAndUTsService {
	
	public List<String> getStateAndUTsList();
	public List<String> getDistrictList(String stateName) throws NoSuchElementException;
	
}
