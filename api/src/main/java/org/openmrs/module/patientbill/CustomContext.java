package org.openmrs.module.patientbill;

import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(Constants.COMPONENT_CUSTOM_CONTEXT)
public class CustomContext {

	@Autowired
	@Qualifier("patientService")
	protected PatientService patientService;

	public PatientCustomService getPatientBillService() {
		return Context.getService(PatientCustomService.class);
	}

	public ServiceCatalogService getCatalogService() {
		return Context.getService(ServiceCatalogService.class);
	}

	public InvoiceService getInvoiceService() {
		return Context.getService(InvoiceService.class);
	}

}
