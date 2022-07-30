package org.openmrs.module.patientbill.rest;

import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.Constants;
import org.openmrs.module.patientbill.CustomContext;
import org.openmrs.module.patientbill.api.entity.Invoice;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/invoice", supportedClass = Invoice.class, supportedOpenmrsVersions = { "2.2.*",
        "2.3.*", "2.4.*", "2.5.*", "2.6.*" })
public class InvoiceResource extends DelegatingCrudResource<Invoice> {
	
	private CustomContext ctx = Context.getRegisteredComponent(Constants.COMPONENT_CUSTOM_CONTEXT, CustomContext.class);
	
	@Override
	public Invoice getByUniqueId(String Uuid) {
		return ctx.getInvoiceService().getInvoiceByUuid(Uuid);
	}
	
	@Override
	protected void delete(Invoice invoice, String s, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public Invoice newDelegate() {
		return new Invoice();
	}
	
	@Override
	public Invoice save(Invoice invoice) {
		ctx.getInvoiceService().saveInvoice(invoice);
		return invoice;
	}
	
	@Override
	public void purge(Invoice invoice, RequestContext requestContext) throws ResponseException {
		if (invoice == null)
			return;
		ctx.getInvoiceService().purgeInvoice(invoice);
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("uuid");
		description.addSelfLink();
		return description;
	}
	
	@Override
	protected NeedsPaging<Invoice> doGetAll(RequestContext context) throws ResponseException {
		return new NeedsPaging<Invoice>(ctx.getInvoiceService().getAllInvoices(), context);
	}
}
