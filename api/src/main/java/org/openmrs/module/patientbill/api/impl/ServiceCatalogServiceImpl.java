package org.openmrs.module.patientbill.api.impl;

import java.util.List;

import org.openmrs.module.patientbill.ServiceCatalog;
import org.openmrs.module.patientbill.ServiceCatalogService;
import org.openmrs.module.patientbill.api.dao.ServiceCatalogDao;
import org.springframework.transaction.annotation.Transactional;

public class ServiceCatalogServiceImpl implements ServiceCatalogService {
	
	private ServiceCatalogDao CatalogDao;
	
	public void setCatalogDao(ServiceCatalogDao catalogDao) {
		this.CatalogDao = catalogDao;
	}
	
	@Override
	public List<ServiceCatalog> getAllCatalogServices() {
		return CatalogDao.getAllCatalogServices();
	}
	
	@Override
	public ServiceCatalog getCatalogService(Integer serviceCatalogId) {
		return CatalogDao.getCatalogService(serviceCatalogId);
	}
	
	@Override
	public ServiceCatalog getServiceCatalogByUuid(String uuid) {
		return CatalogDao.getServiceCatalogByUuid(uuid);
	}
	
	@Override
	public ServiceCatalog saveServiceCatalog(ServiceCatalog serviceCatalog) {
		return CatalogDao.saveServiceCatalog(serviceCatalog);
	}
	
	@Override
	public void purgeServiceCatalog(ServiceCatalog serviceCatalog) {
		CatalogDao.purgeVisitType(serviceCatalog);
	}
}
