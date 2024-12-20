package com.team10.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;

import com.team10.dto.HospitalDTO;
import com.team10.vo.HospitalVO;

public interface IHospitalService {

	public void saveHospital(HospitalDTO hospitalDTO)
			throws IllegalArgumentException, OptimisticLockingFailureException, DataIntegrityViolationException;
	
	public List<HospitalVO> getHospitalsToBeActivated();

}
