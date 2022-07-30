package org.openmrs.module.patientbill.rest;

import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.Constants;
import org.openmrs.module.patientbill.CustomContext;
import org.openmrs.module.patientbill.api.entity.Invoice;
import org.openmrs.module.patientbill.api.entity.InvoiceItem;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/invoiceitem", supportedClass = Invoice.class, supportedOpenmrsVersions = {
        "2.2.*", "2.3.*", "2.4.*", "2.5.*", "2.6.*" })
public class InvoiceItemResource extends DelegatingCrudResource<InvoiceItem> {

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
		ctx.getInvoiceItemService().saveInvoiceItem(invoiceItem);
		return invoiceItem;
	}

	@Override
	public void purge(InvoiceItem invoiceItem, RequestContext requestContext) throws ResponseException {
		ctx.getInvoiceItemService().purgeInvoiceItem(invoiceItem);
	}

	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("uuid");
		description.addSelfLink();
		return description;
	}

	@Override
	protected NeedsPaging<InvoiceItem> doGetAll(RequestContext context) throws ResponseException {
		return new NeedsPaging<>(ctx.getInvoiceItemService().getAllInvoiceItems(), context);
	}
}
