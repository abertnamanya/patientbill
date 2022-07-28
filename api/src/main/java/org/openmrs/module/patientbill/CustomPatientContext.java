package org.openmrs.module.patientbill;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(Constants.COMPONENT_PATIENT_BILL_CONTEXT)
public class CustomPatientContext {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("patientService")
	protected PatientService patientService;
	
	public PatientCustomService getPatientBillService() {
		return Context.getService(PatientCustomService.class);
	}
	
	public PatientService getPatientService() {
		return patientService;
	}
	
}
