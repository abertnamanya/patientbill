package org.openmrs.module.patientbill.api.service.impl;

import java.util.List;

import lombok.Setter;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientbill.api.entity.Invoice;
import org.openmrs.module.patientbill.api.db.impl.InvoiceDaoImpl;
import org.openmrs.module.patientbill.api.service.InvoiceService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InvoiceServiceImpl extends BaseOpenmrsService implements InvoiceService {
	
	@Setter
	private InvoiceDaoImpl dao;
	
	@Override
	public List<Invoice> getAllInvoices() {
		return dao.getAllInvoices();
	}
	
	@Override
	public List<Invoice> searchInvoiceByInvoiceNo(String invoiceNo) {
		return dao.searchInvoiceByInvoiceNo(invoiceNo);
	}
	
	@Override
	public Invoice getInvoice(Integer invoiceId) {
		return dao.getInvoice(invoiceId);
	}
	
	@Override
	public Invoice getInvoiceByUuid(String uuid) {
		return dao.getInvoiceByUuid(uuid);
	}
	
	@Override
	public Invoice saveInvoice(Invoice invoice) {
		return dao.saveInvoice(invoice);
	}
	
	@Override
	public void purgeInvoice(Invoice invoice) {
		dao.purgeInvoice(invoice);
	}
}
