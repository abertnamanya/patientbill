package org.openmrs.module.patientbill.api.dao;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.openmrs.module.patientbill.InvoiceItem;

@Repository("invoiceItem.InvoiceItemDao")
public class InvoiceItemDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional(readOnly = true)
	public List<InvoiceItem> getAllInvoiceItems() {
		return (List<InvoiceItem>) sessionFactory.getCurrentSession().createCriteria(InvoiceItem.class).list();
	}
	
	@Transactional(readOnly = true)
	public InvoiceItem getInvoiceItem(Integer invoiceItemId) {
		return (InvoiceItem) sessionFactory.getCurrentSession().get(InvoiceItem.class, invoiceItemId);
	}
	
	@Transactional(readOnly = true)
	public InvoiceItem getInvoiceItemByUuid(String uuid) {
		return (InvoiceItem) sessionFactory.getCurrentSession().createCriteria(InvoiceItem.class)
		        .add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	@Transactional
	public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
		sessionFactory.getCurrentSession().saveOrUpdate(invoiceItem);
		return invoiceItem;
	}
	
	@Transactional
	public void purgeInvoiceItem(InvoiceItem invoiceItem) {
		sessionFactory.getCurrentSession().delete(invoiceItem);
	}
}
