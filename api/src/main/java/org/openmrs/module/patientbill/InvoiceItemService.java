package org.openmrs.module.patientbill;

import java.util.List;

public interface InvoiceItemService {
	
	List<InvoiceItem> getAllInvoiceItems();
	
	InvoiceItem getInvoiceItem(Integer invoiceItemId);
	
	InvoiceItem getInvoiceItemByUuid(String uuid);
	
	InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem);
	
	void purgeInvoiceItem(InvoiceItem invoiceItem);
}
