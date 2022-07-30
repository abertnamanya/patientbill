package org.openmrs.module.patientbill.api.service.impl;

import java.util.List;

import lombok.Setter;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientbill.api.db.dao.InvoiceItemDao;
import org.openmrs.module.patientbill.api.entity.InvoiceItem;
import org.openmrs.module.patientbill.api.service.InvoiceItemService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InvoiceItemServiceImpl extends BaseOpenmrsService implements InvoiceItemService {
	
	@Setter
	private InvoiceItemDao daoItem;
	
	@Override
	public List<InvoiceItem> getAllInvoiceItems() {
		return daoItem.getAllInvoiceItems();
	}
	
	@Override
	public InvoiceItem getInvoiceItem(Integer invoiceItemId) {
		return daoItem.getInvoiceItem(invoiceItemId);
	}
	
	@Override
	public InvoiceItem getInvoiceItemByUuid(String uuid) {
		return daoItem.getInvoiceItemByUuid(uuid);
	}
	
	@Override
	public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
		return daoItem.saveInvoiceItem(invoiceItem);
	}
	
	@Override
	public void purgeInvoiceItem(InvoiceItem invoiceItem) {
		daoItem.purgeInvoiceItem(invoiceItem);
	}
}
