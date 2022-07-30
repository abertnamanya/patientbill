package org.openmrs.module.patientbill.api.db.dao;

import java.util.List;

import lombok.Setter;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.patientbill.api.entity.ServiceCatalog;
import org.openmrs.module.patientbill.api.db.impl.ServiceCatalogDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class ServiceCatalogDao implements ServiceCatalogDaoImpl {
	
	private static final Logger log = LoggerFactory.getLogger(ServiceCatalogDao.class);
	
	@Setter
	private DbSessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public List<ServiceCatalog> getAllCatalogServices() {
		return (List<ServiceCatalog>) sessionFactory.getCurrentSession().createCriteria(ServiceCatalog.class).list();
	}
	
	@Override
	@Transactional(readOnly = true)
	public ServiceCatalog getCatalogService(Integer serviceCatalogId) {
		return (ServiceCatalog) sessionFactory.getCurrentSession().get(ServiceCatalog.class, serviceCatalogId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ServiceCatalog getServiceCatalogByUuid(String uuid) {
		return (ServiceCatalog) sessionFactory.getCurrentSession()
		        .createQuery("from ServiceCatalog service_cat where service_cat.uuid = :uuid").setParameter("uuid", uuid)
		        .uniqueResult();
	}
	
	@Override
	@Transactional
	public ServiceCatalog saveServiceCatalog(ServiceCatalog serviceCatalog) {
		sessionFactory.getCurrentSession().saveOrUpdate(serviceCatalog);
		return serviceCatalog;
	}
	
	@Override
	@Transactional
	public void purgeServiceCatalog(ServiceCatalog serviceCatalog) {
		sessionFactory.getCurrentSession().delete(serviceCatalog);
	}
}
