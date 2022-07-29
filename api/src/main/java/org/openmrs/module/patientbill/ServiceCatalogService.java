package org.openmrs.module.patientbill;

import java.util.List;

public interface ServiceCatalogService {
	
	List<ServiceCatalog> getAllCatalogServices();
	
	ServiceCatalog getCatalogService(Integer serviceCatalogId);
	
	ServiceCatalog getServiceCatalogByUuid(String uuid);
	
	ServiceCatalog saveServiceCatalog(ServiceCatalog serviceCatalog);
	
	void purgeServiceCatalog(ServiceCatalog serviceCatalog);
}
