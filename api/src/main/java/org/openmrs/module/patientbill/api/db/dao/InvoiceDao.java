package org.openmrs.module.patientbill.api.db.dao;

import java.util.List;

import lombok.Setter;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.patientbill.api.entity.Invoice;
import org.openmrs.module.patientbill.api.db.impl.InvoiceDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class InvoiceDao implements InvoiceDaoImpl {
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceDao.class);
	
	@Setter
	DbSessionFactory sessionFactory;
	
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
