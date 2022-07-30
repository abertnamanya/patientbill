package org.openmrs.module.patientbill.api.service;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.patientbill.api.entity.Invoice;

public interface InvoiceService extends OpenmrsService {
	
	List<Invoice> getAllInvoices();
	
	Invoice getInvoice(Integer invoiceId);
	
	Invoice getInvoiceByUuid(String uuid);
	
	Invoice saveInvoice(Invoice invoice);
	
	void purgeInvoice(Invoice invoice);
}
