package org.openmrs.module.patientbill.api.service.impl;

import java.util.List;

import lombok.Setter;
import org.openmrs.Patient;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientbill.api.db.impl.PatientCustomDaoImpl;
import org.openmrs.module.patientbill.api.service.PatientCustomService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PatientCustomServiceImpl extends BaseOpenmrsService implements PatientCustomService {
	
	@Setter
	PatientCustomDaoImpl dao;
	
	@Override
	public List<Patient> searchPatientByName(String searchName) {
		return dao.searchPatientByName(searchName);
	}
	
	@Override
	public List<Patient> searchPatientByMobileNo(String mobileNo) {
		return dao.searchPatientByMobileNo(mobileNo);
	}
	
}
