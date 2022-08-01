package org.openmrs.module.patientbill.api.service.impl;

import java.util.List;

import lombok.Setter;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientbill.api.db.dao.InvoiceItemDao;
import org.openmrs.module.patientbill.api.db.impl.InvoiceItemDaoImpl;
import org.openmrs.module.patientbill.api.entity.InvoiceItem;
import org.openmrs.module.patientbill.api.service.InvoiceItemService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InvoiceItemServiceImpl extends BaseOpenmrsService implements InvoiceItemService {
	
	@Setter
	private InvoiceItemDaoImpl dao;
	
	@Override
	public List<InvoiceItem> getAllInvoiceItems() {
		return dao.getAllInvoiceItems();
	}
	
	@Override
	public InvoiceItem getInvoiceItem(Integer invoiceItemId) {
		return dao.getInvoiceItem(invoiceItemId);
	}
	
	@Override
	public InvoiceItem getInvoiceItemByUuid(String uuid) {
		return dao.getInvoiceItemByUuid(uuid);
	}
	
	@Override
	public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
		return dao.saveInvoiceItem(invoiceItem);
	}
	
	@Override
	public void purgeInvoiceItem(InvoiceItem invoiceItem) {
		dao.purgeInvoiceItem(invoiceItem);
	}
}
