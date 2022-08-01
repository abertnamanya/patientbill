package org.openmrs.module.patientbill.api.service;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.patientbill.api.entity.InvoiceItem;

public interface InvoiceItemService extends OpenmrsService {
	
	List<InvoiceItem> getAllInvoiceItems();
	
	InvoiceItem getInvoiceItem(Integer invoiceItemId);
	
	InvoiceItem getInvoiceItemByUuid(String uuid);
	
	InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem);
	
	void purgeInvoiceItem(InvoiceItem invoiceItem);
}
