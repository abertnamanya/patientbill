package org.openmrs.module.patientbill.rest;

;
import java.util.List;
import java.util.Set;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.BooleanProperty;
import io.swagger.models.properties.DateProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.Constants;
import org.openmrs.module.patientbill.CustomContext;
import org.openmrs.module.patientbill.api.entity.Invoice;
import org.openmrs.module.patientbill.api.entity.InvoiceItem;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.EmptySearchResult;
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
	public Model getCREATEModel(Representation rep) {

		ModelImpl model = new ModelImpl()
				.property("invoiceNo", new StringProperty())
				.property("patient", new StringProperty().example("uuid"))
				.required("patient");
		if (rep instanceof FullRepresentation) {
			model.property("patient", new RefProperty("#/definitions/PatientCreate"));
			//					.property("invoiceItems", new ArrayProperty(new RefProperty("#/definitions/InvoiceItemCreate")));
		}
		return model;
	}

	@Override
	public Invoice save(Invoice invoice) {
		ctx.getInvoiceService().saveInvoice(invoice);
		return invoice;
	}

	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("patient");
		/** no required properties**/
		description.addProperty("invoiceNo");
		//		description.addProperty("invoiceItems");

		return description;
	}

	@Override
	public void purge(Invoice invoice, RequestContext requestContext) throws ResponseException {
		if (invoice == null)
			return;
		ctx.getInvoiceService().purgeInvoice(invoice);
	}

	@PropertySetter("invoiceItems")
	public static void setInvoiceItems(Invoice invoice, Set<InvoiceItem> items) {
		for (InvoiceItem item : items)
			invoice.addInvoiceItem(item);
	}

	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		if (representation instanceof DefaultRepresentation || representation instanceof RefRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("invoiceNo");
			description.addProperty("patient", Representation.DEFAULT);
			//description.addProperty("invoiceItems", Representation.DEFAULT);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (representation instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("invoiceNo");
			description.addProperty("patient", Representation.DEFAULT);
			//description.addProperty("invoiceItems", Representation.DEFAULT);
			description.addProperty("auditInfo");
			description.addSelfLink();
			return description;
		}
		return null;
	}

	@Override
	public Model getGETModel(Representation rep) {
		ModelImpl modelImpl = (ModelImpl) super.getGETModel(rep);
		if (rep instanceof DefaultRepresentation || rep instanceof FullRepresentation) {
			modelImpl.property("uuid", new StringProperty());
		}
		if (rep instanceof DefaultRepresentation) {
			modelImpl.property("patient", new RefProperty("#/definitions/PatientGetRef"));
			//.property("invoiceItems", new ArrayProperty(new RefProperty("#/definitions/InvoiceItemGetRef")));
		} else if (rep instanceof FullRepresentation) {
			modelImpl.property("patient", new RefProperty("#/definitions/PatientGet"));
			//.property("invoiceItems", new ArrayProperty(new RefProperty("#/definitions/InvoiceItemGet")));
		}
		return modelImpl;
	}

	@Override
	protected NeedsPaging<Invoice> doGetAll(RequestContext context) throws ResponseException {
		return new NeedsPaging<Invoice>(ctx.getInvoiceService().getAllInvoices(), context);
	}

	@Override
	protected PageableResult doSearch(RequestContext context) {
		String billNo = context.getRequest().getParameter("bill_no");
		List<Invoice> invoiceList = ctx.getInvoiceService().searchInvoiceByInvoiceNo(billNo);
		if (invoiceList != null) {
			return new NeedsPaging<>(invoiceList, context);
		}
		return new EmptySearchResult();
	}
}
