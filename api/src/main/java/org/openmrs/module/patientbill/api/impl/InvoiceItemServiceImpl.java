package org.openmrs.module.patientbill.api.impl;

import java.util.List;

import org.openmrs.module.patientbill.InvoiceItem;
import org.openmrs.module.patientbill.InvoiceItemService;
import org.openmrs.module.patientbill.api.dao.InvoiceItemDao;

public class InvoiceItemServiceImpl implements InvoiceItemService {
	
	private InvoiceItemDao invoiceItemDao;
	
	public void setInvoiceItemDao(InvoiceItemDao invoiceItemDao) {
		this.invoiceItemDao = invoiceItemDao;
	}
	
	@Override
	public List<InvoiceItem> getAllInvoiceItems() {
		return invoiceItemDao.getAllInvoiceItems();
	}
	
	@Override
	public InvoiceItem getInvoiceItem(Integer invoiceItemId) {
		return invoiceItemDao.getInvoiceItem(invoiceItemId);
	}
	
	@Override
	public InvoiceItem getInvoiceItemByUuid(String uuid) {
		return invoiceItemDao.getInvoiceItemByUuid(uuid);
	}
	
	@Override
	public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
		return invoiceItemDao.saveInvoiceItem(invoiceItem);
	}
	
	@Override
	public void purgeInvoiceItem(InvoiceItem invoiceItem) {
		invoiceItemDao.purgeInvoiceItem(invoiceItem);
	}
}
