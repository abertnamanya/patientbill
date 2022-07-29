package org.openmrs.module.patientbill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.module.patientbill.Invoice;
import org.openmrs.module.patientbill.ServiceCatalog;

@Entity
@Table(name = "invoice_item")
public class InvoiceItem extends BaseOpenmrsObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_item_id", nullable = false)
	private Integer invoiceItemId;
	
	@ManyToOne
	@Column(name = "invoice_id")
	private Invoice invoice;
	
	@ManyToOne
	@Column(name = "service_catalog_id")
	private ServiceCatalog serviceCatalog;
	
	public InvoiceItem() {
	}
	
	public Integer getInvoiceItemId() {
		return invoiceItemId;
	}
	
	public void setInvoiceItemId(Integer invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public ServiceCatalog getServiceCatalog() {
		return serviceCatalog;
	}
	
	public void setServiceCatalog(ServiceCatalog serviceCatalog) {
		this.serviceCatalog = serviceCatalog;
	}
	
	@Override
	public Integer getId() {
		return getInvoiceItemId();
	}
	
	@Override
	public void setId(Integer integer) {
		setInvoiceItemId(integer);
	}
}