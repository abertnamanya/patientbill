package org.openmrs.module.patientbill;

import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.api.service.InvoiceItemService;
import org.openmrs.module.patientbill.api.service.InvoiceService;
import org.openmrs.module.patientbill.api.service.PatientCustomService;
import org.openmrs.module.patientbill.api.service.ServiceCatalogService;
import org.springframework.stereotype.Component;

@Component(Constants.COMPONENT_CUSTOM_CONTEXT)
public class CustomContext {
	
	public PatientCustomService getPatientBillService() {
		return Context.getService(PatientCustomService.class);
	}
	
	public ServiceCatalogService getCatalogService() {
		return Context.getService(ServiceCatalogService.class);
	}
	
	public InvoiceService getInvoiceService() {
		return Context.getService(InvoiceService.class);
	}
	
	public InvoiceItemService getInvoiceItemService() {
		return Context.getService(InvoiceItemService.class);
	}
	
}
