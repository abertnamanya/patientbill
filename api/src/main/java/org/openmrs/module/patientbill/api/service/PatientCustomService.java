package org.openmrs.module.patientbill.api.service;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;

public interface PatientCustomService extends OpenmrsService {
	
	List<Patient> searchPatientByName(String searchName);
	
	List<Patient> searchPatientByMobileNo(String mobileNo);
}
