package org.openmrs.module.patientbill.api.dao;

import java.util.List;

import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.patientbill.ServiceCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("serviceCatalog.ServiceCatalogDao")
public class ServiceCatalogDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional(readOnly = true)
	public List<ServiceCatalog> getAllCatalogServices() {
		return (List<ServiceCatalog>) sessionFactory.getCurrentSession().createCriteria(ServiceCatalog.class).list();
	}
	
	@Transactional(readOnly = true)
	public ServiceCatalog getCatalogService(Integer serviceCatalogId) {
		return (ServiceCatalog) sessionFactory.getCurrentSession().get(ServiceCatalog.class, serviceCatalogId);
	}
	
	@Transactional(readOnly = true)
	public ServiceCatalog getServiceCatalogByUuid(String uuid) {
		return (ServiceCatalog) sessionFactory.getCurrentSession()
		        .createQuery("from ServiceCatalog service_cat where service_cat.uuid = :uuid").setParameter("uuid", uuid)
		        .uniqueResult();
	}
	
	@Transactional
	public ServiceCatalog saveServiceCatalog(ServiceCatalog serviceCatalog) {
		sessionFactory.getCurrentSession().saveOrUpdate(serviceCatalog);
		return serviceCatalog;
	}
	
	@Transactional
	public void purgeVisitType(ServiceCatalog serviceCatalog) {
		sessionFactory.getCurrentSession().delete(serviceCatalog);
	}
	
}
