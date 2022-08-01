package org.openmrs.module.patientbill.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_id", nullable = false)
	private Integer InvoiceId;
	
	//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	//	@SequenceGenerator(name = "generator", sequenceName = "idgen_seq_id_gen")
	@Column(name = "invoice_no")
	private String InvoiceNo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@OneToMany(mappedBy = "invoice")
	private Set<InvoiceItem> invoiceItems;
	
	public Invoice() {
	}
	
	public Integer getInvoiceId() {
		return InvoiceId;
	}
	
	public void setInvoiceId(Integer invoiceId) {
		this.InvoiceId = invoiceId;
	}
	
	@Override
	public Integer getId() {
		return getInvoiceId();
	}
	
	@Override
	public void setId(Integer integer) {
		setInvoiceId(integer);
	}
	
	public String getInvoiceNo() {
		return InvoiceNo;
	}
	
	public void setInvoiceNo(String invoiceNo) {
		InvoiceNo = invoiceNo;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Set<InvoiceItem> getInvoiceItems() {
		if (invoiceItems == null) {
			invoiceItems = new HashSet<>();
		}
		return invoiceItems;
	}
	
	public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	
	public void addInvoiceItem(InvoiceItem invoiceItem) {
		if (invoiceItem != null) {
			invoiceItem.setInvoice(this);
			getInvoiceItems().add(invoiceItem);
		}
	}
}
