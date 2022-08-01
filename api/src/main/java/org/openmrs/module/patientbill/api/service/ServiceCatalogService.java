package org.openmrs.module.patientbill.api.service;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.patientbill.api.entity.ServiceCatalog;

public interface ServiceCatalogService extends OpenmrsService {
	
	List<ServiceCatalog> getAllCatalogServices();
	
	ServiceCatalog getCatalogService(Integer serviceCatalogId);
	
	ServiceCatalog getServiceCatalogByUuid(String uuid);
	
	ServiceCatalog saveServiceCatalog(ServiceCatalog serviceCatalog);
	
	void purgeServiceCatalog(ServiceCatalog serviceCatalog);
}
