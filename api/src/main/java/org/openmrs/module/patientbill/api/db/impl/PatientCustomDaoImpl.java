package org.openmrs.module.patientbill.api.db.impl;

import java.util.List;

import org.openmrs.Patient;

public interface PatientCustomDaoImpl {
	
	List<Patient> searchPatientByName(String searchName);
	
	List<Patient> searchPatientByMobileNo(String mobileNo);
}
