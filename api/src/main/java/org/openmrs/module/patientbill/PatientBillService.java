package org.openmrs.module.patientbill;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;

public interface PatientBillService {
	
	List<Patient> searchPatientByName(String searchName);
	
	List<Patient> searchPatientByMobileNo(String mobileNo);
}
