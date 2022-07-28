package org.openmrs.module.patientbill.rest;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.Constants;
import org.openmrs.module.patientbill.CustomPatientContext;
import org.openmrs.module.patientbill.PatientBillService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.EmptySearchResult;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.GenericRestException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Resource(name = RestConstants.VERSION_1 + "/searchpatient", supportedClass = Patient.class, supportedOpenmrsVersions = {
        "2.2.*", "2.3.*", "2.4.*", "2.5.*", "2.6.*" })
public class PatientBillResource extends DataDelegatingCrudResource<Patient> {
	
	private static final Logger log = LoggerFactory.getLogger(PatientBillResource.class);
	
	private CustomPatientContext ctx = Context.getRegisteredComponent(Constants.COMPONENT_PATIENT_BILL_CONTEXT,
	    CustomPatientContext.class);
	
	@Override
	public Patient getByUniqueId(String uuid) {
		Patient patient = Context.getPatientService().getPatientByUuid(uuid);
		if (patient == null) {
			throw new GenericRestException("Patient Uuid provided does not exist");
		}
		return patient;
	}
	
	@Override
	protected void delete(Patient patient, String s, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public Patient newDelegate() {
		return new Patient();
	}
	
	@Override
	public Patient save(Patient patient) {
		return null;
	}
	
	@Override
	public void purge(Patient patient, RequestContext requestContext) throws ResponseException {
		
	}
	
	@PropertyGetter("person")
	public static Person getPerson(Patient instance) {
		return new Person(instance);
	}
	
	@PropertySetter("person")
	public static void setPerson(Patient instance, String personUuid) {
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("uuid");
		description.addProperty("person", Representation.DEFAULT);
		description.addSelfLink();
		return description;
	}
	
	//	@Override
	//	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
	//		if (rep instanceof DefaultRepresentation) {
	//			DelegatingResourceDescription description = new DelegatingResourceDescription();
	//			description.addProperty("uuid");
	//			description.addProperty("display");
	//			//			description.addProperty("identifiers", Representation.REF);
	//			//			description.addProperty("person", Representation.DEFAULT);
	//			description.addProperty("voided");
	//			description.addSelfLink();
	//			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
	//			return description;
	//		} else if (rep instanceof FullRepresentation) {
	//			DelegatingResourceDescription description = new DelegatingResourceDescription();
	//			description.addProperty("uuid");
	//			description.addProperty("display");
	//			//			description.addProperty("identifiers", Representation.DEFAULT);
	//			//			description.addProperty("person", Representation.FULL);
	//			description.addProperty("voided");
	//			description.addProperty("auditInfo");
	//			description.addSelfLink();
	//			return description;
	//		}
	//		return null;
	//	}
	//
	public List<Patient> searchPatient(PatientBillService as, String searchName, String mobileNo) {
		if (searchName != null) {
			return as.searchPatientByName(searchName);
		} else if (mobileNo != null) {
			return as.searchPatientByMobileNo(mobileNo);
		}
		return null;
	}
	
	@Override
	protected PageableResult doSearch(RequestContext context) {
		String searchName = context.getRequest().getParameter("search_name");
		String mobileNo = context.getRequest().getParameter("mobile_number");

		List<Patient> patientList = searchPatient(ctx.getPatientBillService(), searchName, mobileNo);
		if (patientList != null) {
			return new NeedsPaging<>(patientList, context);
		}
		return new EmptySearchResult();
	}
}
