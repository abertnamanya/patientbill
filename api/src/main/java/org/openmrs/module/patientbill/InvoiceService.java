package org.openmrs.module.patientbill;

import java.util.List;

public interface InvoiceService {
	
	List<Invoice> getAllInvoices();
	
	Invoice getInvoice(Integer invoiceId);
	
	Invoice getInvoiceByUuid(String uuid);
	
	Invoice saveInvoice(Invoice invoice);
	
	void purgeInvoice(Invoice invoice);
}
