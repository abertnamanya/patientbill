package org.openmrs.module.patientbill.rest;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.Constants;
import org.openmrs.module.patientbill.CustomContext;
import org.openmrs.module.patientbill.api.entity.Invoice;
import org.openmrs.module.patientbill.api.entity.InvoiceItem;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/invoiceitem", supportedClass = Invoice.class, supportedOpenmrsVersions = {
        "2.2.*", "2.3.*", "2.4.*", "2.5.*", "2.6.*" })
public class InvoiceItemResource extends DataDelegatingCrudResource<InvoiceItem> {
	
	private CustomContext ctx = Context.getRegisteredComponent(Constants.COMPONENT_CUSTOM_CONTEXT, CustomContext.class);
	
	@Override
	public InvoiceItem getByUniqueId(String Uuid) {
		return ctx.getInvoiceItemService().getInvoiceItemByUuid(Uuid);
	}
	
	@Override
	protected void delete(InvoiceItem invoiceItem, String s, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public InvoiceItem newDelegate() {
		return new InvoiceItem();
	}
	
	@Override
	public InvoiceItem save(InvoiceItem invoiceItem) {
		return ctx.getInvoiceItemService().saveInvoiceItem(invoiceItem);
	}
	
	@Override
	public void purge(InvoiceItem invoiceItem, RequestContext requestContext) throws ResponseException {
		ctx.getInvoiceItemService().purgeInvoiceItem(invoiceItem);
	}
	
	@PropertyGetter("display")
	public String getDisplayString(InvoiceItem item) {
		return item.getServiceCatalog().getConcept().getName().getConcept().getName().getName();
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		if (representation instanceof DefaultRepresentation || representation instanceof RefRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("display");
			description.addProperty("serviceCatalog", Representation.REF);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (representation instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("invoice", Representation.REF);
			description.addProperty("serviceCatalog", Representation.REF);
			description.addProperty("auditInfo");
			description.addSelfLink();
			return description;
		}
		return null;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("invoice");
		description.addProperty("serviceCatalog");
		return description;
	}
	
	@Override
	public Model getGETModel(Representation rep) {
		ModelImpl model = (ModelImpl) super.getGETModel(rep);
		if (rep instanceof DefaultRepresentation || rep instanceof FullRepresentation) {
			model.property("uuid", new StringProperty()).property("display", new StringProperty());
		}
		if (rep instanceof DefaultRepresentation) {
			model.property("serviceCatalog", new RefProperty("#/definitions/ServiceCatalogGetRef"));
		} else if (rep instanceof FullRepresentation) {
			model.property("serviceCatalog", new RefProperty("#/definitions/ServiceCatalogGet"));
		}
		return model;
	}
	
	@Override
	public Model getCREATEModel(Representation rep) {
		
		ModelImpl model = new ModelImpl().property("invoice", new StringProperty().example("uuid"))
		        .property("serviceCatalog", new StringProperty().example("uuid"))
		        
		        .required("invoice").required("serviceCatalog");
		if (rep instanceof FullRepresentation) {
			model.property("invoice", new RefProperty("#/definitions/InvoiceCreate")).property("serviceCatalog",
			    new RefProperty("#/definitions/ServiceCatalogCreate"));
		}
		return model;
	}
	
	@Override
	protected NeedsPaging<InvoiceItem> doGetAll(RequestContext context) throws ResponseException {
		return new NeedsPaging<>(ctx.getInvoiceItemService().getAllInvoiceItems(), context);
	}
	
	@Override
	public Model getUPDATEModel(Representation rep) {
		return new ModelImpl().property("invoice", new RefProperty("#/definitions/InvoiceCreate")).property(
		    "serviceCatalog", new RefProperty("#/definitions/ServiceCatalogCreate"));
	}
}
