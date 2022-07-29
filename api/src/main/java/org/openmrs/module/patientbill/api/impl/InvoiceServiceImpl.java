package org.openmrs.module.patientbill.api.impl;

import java.util.List;

import org.openmrs.module.patientbill.Invoice;
import org.openmrs.module.patientbill.InvoiceService;
import org.openmrs.module.patientbill.api.dao.InvoiceDao;

public class InvoiceServiceImpl implements InvoiceService {
	
	private InvoiceDao invoiceDao;
	
	public void setInvoiceDao(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}
	
	@Override
	public List<Invoice> getAllInvoices() {
		return invoiceDao.getAllInvoices();
	}
	
	@Override
	public Invoice getInvoice(Integer invoiceId) {
		return invoiceDao.getInvoice(invoiceId);
	}
	
	@Override
	public Invoice getInvoiceByUuid(String uuid) {
		return invoiceDao.getInvoiceByUuid(uuid);
	}
	
	@Override
	public Invoice saveInvoice(Invoice invoice) {
		return invoiceDao.saveInvoice(invoice);
	}
	
	@Override
	public void purgeInvoice(Invoice invoice) {
		invoiceDao.purgeInvoice(invoice);
	}
}
