package org.openmrs.module.patientbill.api.service.impl;

import java.util.List;

import lombok.Setter;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientbill.api.entity.ServiceCatalog;
import org.openmrs.module.patientbill.api.service.ServiceCatalogService;
import org.openmrs.module.patientbill.api.db.impl.ServiceCatalogDaoImpl;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ServiceCatalogServiceImpl extends BaseOpenmrsService implements ServiceCatalogService {
	
	@Setter
	private ServiceCatalogDaoImpl dao;
	
	@Override
	public List<ServiceCatalog> getAllCatalogServices() {
		return dao.getAllCatalogServices();
	}
	
	@Override
	public ServiceCatalog getCatalogService(Integer serviceCatalogId) {
		return dao.getCatalogService(serviceCatalogId);
	}
	
	@Override
	public ServiceCatalog getServiceCatalogByUuid(String uuid) {
		return dao.getServiceCatalogByUuid(uuid);
	}
	
	@Override
	public ServiceCatalog saveServiceCatalog(ServiceCatalog serviceCatalog) {
		return dao.saveServiceCatalog(serviceCatalog);
	}
	
	@Override
	public void purgeServiceCatalog(ServiceCatalog serviceCatalog) {
		dao.purgeServiceCatalog(serviceCatalog);
	}
}
