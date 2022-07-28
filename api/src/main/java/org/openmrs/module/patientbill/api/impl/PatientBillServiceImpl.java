package org.openmrs.module.patientbill.api.impl;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.module.patientbill.PatientBillService;
import org.openmrs.module.patientbill.api.dao.PatientBillDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PatientBillServiceImpl implements PatientBillService {
	
	PatientBillDao dao;
	
	public void setDao(PatientBillDao dao) {
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
