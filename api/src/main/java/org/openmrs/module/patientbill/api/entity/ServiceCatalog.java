package org.openmrs.module.patientbill.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Concept;

@Entity
@Table(name = "service_catalog")
public class ServiceCatalog extends BaseOpenmrsObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_catalog_id", nullable = false)
	private Integer serviceCatalogId;
	
	@ManyToOne
	@JoinColumn(name = "concept_id")
	private Concept concept;
	
	@Column(name = "price")
	private double servicePrice;
	
	public ServiceCatalog() {
	}
	
	public Integer getServiceCatalogId() {
		return serviceCatalogId;
	}
	
	public void setServiceCatalogId(Integer serviceCatalogId) {
		this.serviceCatalogId = serviceCatalogId;
	}
	
	public Concept getConcept() {
		return concept;
	}
	
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public double getServicePrice() {
		return servicePrice;
	}
	
	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}
	
	@Override
	public Integer getId() {
		return getServiceCatalogId();
	}
	
	@Override
	public void setId(Integer integer) {
		setServiceCatalogId(integer);
	}
}
