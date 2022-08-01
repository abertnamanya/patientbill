package org.openmrs.module.patientbill.api.db.dao;

import lombok.Setter;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.patientbill.api.db.impl.InvoiceItemDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.openmrs.module.patientbill.api.entity.InvoiceItem;

public class InvoiceItemDao implements InvoiceItemDaoImpl {
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceItemDao.class);
	
	@Setter
	DbSessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public List<InvoiceItem> getAllInvoiceItems() {
		return (List<InvoiceItem>) sessionFactory.getCurrentSession().createCriteria(InvoiceItem.class).list();
	}
	
	@Override
	@Transactional(readOnly = true)
	public InvoiceItem getInvoiceItem(Integer invoiceItemId) {
		return (InvoiceItem) sessionFactory.getCurrentSession().get(InvoiceItem.class, invoiceItemId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public InvoiceItem getInvoiceItemByUuid(String uuid) {
		return (InvoiceItem) sessionFactory.getCurrentSession().createCriteria(InvoiceItem.class)
		        .add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	@Override
	@Transactional
	public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
		sessionFactory.getCurrentSession().saveOrUpdate(invoiceItem);
		return invoiceItem;
	}
	
	@Override
	@Transactional
	public void purgeInvoiceItem(InvoiceItem invoiceItem) {
		sessionFactory.getCurrentSession().delete(invoiceItem);
	}
}
