package org.openmrs.module.patientbill.api.db.impl;

import java.util.List;

import org.openmrs.module.patientbill.api.entity.ServiceCatalog;

public interface ServiceCatalogDaoImpl {
	
	List<ServiceCatalog> getAllCatalogServices();
	
	ServiceCatalog getCatalogService(Integer serviceCatalogId);
	
	ServiceCatalog getServiceCatalogByUuid(String uuid);
	
	ServiceCatalog saveServiceCatalog(ServiceCatalog serviceCatalog);
	
	void purgeServiceCatalog(ServiceCatalog serviceCatalog);
}
