package org.openmrs.module.patientbill.api.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.patientbill.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("invoice.InvoiceDao")
public class InvoiceDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional(readOnly = true)
	public List<Invoice> getAllInvoices() {
		return (List<Invoice>) sessionFactory.getCurrentSession().createCriteria(Invoice.class).list();
	}
	
	@Transactional(readOnly = true)
	public Invoice getInvoice(Integer invoiceId) {
		return (Invoice) sessionFactory.getCurrentSession().get(Invoice.class, invoiceId);
	}
	
	@Transactional(readOnly = true)
	public Invoice getInvoiceByUuid(String uuid) {
		return (Invoice) sessionFactory.getCurrentSession().createCriteria(Invoice.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	@Transactional
	public Invoice saveInvoice(Invoice invoice) {
		sessionFactory.getCurrentSession().saveOrUpdate(invoice);
		return invoice;
	}
	
	@Transactional
	public void purgeInvoice(Invoice invoice) {
		sessionFactory.getCurrentSession().delete(invoice);
	}
}
