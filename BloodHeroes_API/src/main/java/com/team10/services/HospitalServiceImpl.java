package com.team10.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team10.dto.HospitalDTO;
import com.team10.entity.Hospital;
import com.team10.entity.HospitalService;
import com.team10.entity.ROLE;
import com.team10.entity.User;
import com.team10.repository.HospitalRepository;
import com.team10.vo.HospitalVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements IHospitalService {

	private final HospitalRepository hospitalRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void saveHospital(HospitalDTO hospitalDTO)
			throws IllegalArgumentException, OptimisticLockingFailureException, DataIntegrityViolationException {

		//@formatter:off
		hospitalRepository.save(Hospital.builder()
				.user(User.builder()
						.name(hospitalDTO.getName())
						.email(hospitalDTO.getEmail())
						.password(passwordEncoder.encode(hospitalDTO.getPassword()))
						.contacts(hospitalDTO.getContacts())
						.role(ROLE.ORGANIZATION_MANAGER)
						.isActivated(false)
						.build())
				.registrationNumber(hospitalDTO.getRegistrationNumber())
				.hospitalType(hospitalDTO.getHospitalType())
				.dateOfEstablishment(hospitalDTO.getDateOfEstablishment())
				.accreditation(hospitalDTO.getAccreditation())
				.bedCapacity(hospitalDTO.getBedCapacity())
				.hospitalService(HospitalService.builder()
						.emergencyServices(hospitalDTO.getEmergencyServices())
						.icuAvailable(hospitalDTO.getIcuAvailable())
						.ambulanceServices(hospitalDTO.getAmbulanceServices())
						.laboratoryAvailable(hospitalDTO.getLaboratoryAvailable())
						.pharmacyAvailable(hospitalDTO.getPharmacyAvailable())
						.bloodBankAvailable(hospitalDTO.getBloodBankAvailable())
						.numberOfOperatingTheaters(hospitalDTO.getNumberOfOperatingTheaters())
						.build())
				.build());
		//@formatter:on

	}

	@Override
	public List<HospitalVO> getHospitalsToBeActivated() {
		return hospitalRepository.findByUserIsActicatedFalse().stream().map(
		//@formatter:off
				hospital->{
					return HospitalVO.builder()
								.id(hospital.getUser().getId())
								.name(hospital.getUser().getName())
								.email(hospital.getUser().getEmail())
								.password("")
								.contacts(hospital.getUser().getContacts())
								.registrationNumber(hospital.getRegistrationNumber())
								.hospitalType(hospital.getHospitalType())
								.dateOfEstablishment(hospital.getDateOfEstablishment())
								.accreditation(hospital.getAccreditation())
								.bedCapacity(hospital.getBedCapacity())
								.emergencyServices(hospital.getHospitalService().getEmergencyServices())
								.icuAvailable(hospital.getHospitalService().getIcuAvailable())
								.ambulanceServices(hospital.getHospitalService().getAmbulanceServices())
								.laboratoryAvailable(hospital.getHospitalService().getLaboratoryAvailable())
								.pharmacyAvailable(hospital.getHospitalService().getPharmacyAvailable())
								.bloodBankAvailable(hospital.getHospitalService().getBloodBankAvailable())
								.numberOfOperatingTheaters(hospital.getHospitalService().getNumberOfOperatingTheaters())
								
							.build();
				}
				//@formatter:on
		).collect(Collectors.toList());
	}
}
