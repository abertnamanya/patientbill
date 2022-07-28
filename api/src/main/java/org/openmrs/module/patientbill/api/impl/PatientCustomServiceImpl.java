package org.openmrs.module.patientbill.api.impl;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.module.patientbill.PatientCustomService;
import org.openmrs.module.patientbill.api.dao.PatientCustomDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PatientCustomServiceImpl implements PatientCustomService {
	
	PatientCustomDao dao;
	
	public void setDao(PatientCustomDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Patient> searchPatientByName(String searchName) {
		return dao.searchPatientByName(searchName);
	}
	
	@Override
	public List<Patient> searchPatientByMobileNo(String mobileNo) {
		return dao.searchPatientByMobileNo(mobileNo);
	}
	
}
