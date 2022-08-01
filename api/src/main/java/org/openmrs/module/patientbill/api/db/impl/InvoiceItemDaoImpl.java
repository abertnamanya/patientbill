package org.openmrs.module.patientbill.api.db.impl;

import java.util.List;

import org.openmrs.module.patientbill.api.entity.InvoiceItem;

public interface InvoiceItemDaoImpl {
	
	List<InvoiceItem> getAllInvoiceItems();
	
	InvoiceItem getInvoiceItem(Integer invoiceItemId);
	
	InvoiceItem getInvoiceItemByUuid(String uuid);
	
	InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem);
	
	void purgeInvoiceItem(InvoiceItem invoiceItem);
}
