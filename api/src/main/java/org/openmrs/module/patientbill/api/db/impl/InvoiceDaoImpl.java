package org.openmrs.module.patientbill.api.db.impl;

import java.util.List;

import org.openmrs.module.patientbill.api.entity.Invoice;

public interface InvoiceDaoImpl {
	
	List<Invoice> getAllInvoices();
	
	Invoice getInvoice(Integer invoiceId);
	
	Invoice getInvoiceByUuid(String uuid);
	
	Invoice saveInvoice(Invoice invoice);
	
	void purgeInvoice(Invoice invoice);
}
