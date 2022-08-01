package org.openmrs.module.patientbill.rest;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.Constants;
import org.openmrs.module.patientbill.CustomContext;
import org.openmrs.module.patientbill.api.service.PatientCustomService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
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
public class PatientCustomResource extends DataDelegatingCrudResource<Patient> {
	
	private static final Logger log = LoggerFactory.getLogger(PatientCustomResource.class);
	
	private CustomContext ctx = Context.getRegisteredComponent(Constants.COMPONENT_CUSTOM_CONTEXT, CustomContext.class);
	
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
	
	@PropertyGetter("display")
	public String getDisplayString(Patient patient) {
		if (patient.getPatientIdentifier() == null)
			return "";
		return patient.getPatientIdentifier().getIdentifier() + " - " + patient.getPersonName().getFullName();
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("uuid");
		description.addProperty("display");
		description.addProperty("person", Representation.REF);
		description.addSelfLink();
		return description;
	}
	
	public List<Patient> searchPatient(PatientCustomService as, String searchName, String mobileNo) {
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

		if (searchName == null && mobileNo == null) { /** if both parameters are null **/
			throw new GenericRestException("Please provide the a search parameter either search_name or mobile_number!");
		}
		List<Patient> patientList = searchPatient(ctx.getPatientBillService(), searchName, mobileNo);
		if (patientList != null) {
			return new NeedsPaging<>(patientList, context);
		}
		return new EmptySearchResult();
	}
}
