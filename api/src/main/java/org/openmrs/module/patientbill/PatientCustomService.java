package org.openmrs.module.patientbill;

import java.util.List;

import org.openmrs.Patient;

public interface PatientCustomService {
	
	List<Patient> searchPatientByName(String searchName);
	
	List<Patient> searchPatientByMobileNo(String mobileNo);
}
