package org.openmrs.module.patientbill.rest;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.DoubleProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientbill.Constants;
import org.openmrs.module.patientbill.CustomContext;
import org.openmrs.module.patientbill.api.entity.ServiceCatalog;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/servicecatalog", supportedClass = ServiceCatalog.class, supportedOpenmrsVersions = {
        "2.2.*", "2.3.*", "2.4.*", "2.5.*", "2.6.*" })
public class ServiceCatalogResource extends DelegatingCrudResource<ServiceCatalog> {
	
	private CustomContext ctx = Context.getRegisteredComponent(Constants.COMPONENT_CUSTOM_CONTEXT, CustomContext.class);
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("uuid");
		description.addProperty("concept", Representation.REF);
		description.addProperty("servicePrice");
		return description;
	}
	
	@Override
	public ServiceCatalog getByUniqueId(String Uuid) {
		return ctx.getCatalogService().getServiceCatalogByUuid(Uuid);
	}
	
	@Override
	protected void delete(ServiceCatalog serviceCatalog, String s, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public ServiceCatalog newDelegate() {
		return new ServiceCatalog();
	}
	
	public Model getGETModel(Representation rep) {
		ModelImpl modelImpl = (ModelImpl) super.getGETModel(rep);
		if (rep instanceof DefaultRepresentation || rep instanceof FullRepresentation) {
			modelImpl.property("servicePrice", new DoubleProperty());
		}
		if (rep instanceof DefaultRepresentation) {
			modelImpl.property("concept", new RefProperty("#/definitions/ConceptGetRef"));
		} else if (rep instanceof FullRepresentation) {
			modelImpl.property("concept", new RefProperty("#/definitions/ConceptGet"));
		}
		return modelImpl;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
		DelegatingResourceDescription description = super.getCreatableProperties();
		description.addProperty("concept");
		description.addProperty("servicePrice");
		
		return description;
	}
	
	@Override
	public Model getCREATEModel(Representation rep) {
		ModelImpl model = ((ModelImpl) super.getCREATEModel(rep)).property("concept", new StringProperty().example("uuid"))
		        .property("servicePrice", new DoubleProperty()).required("concept");
		if (rep instanceof FullRepresentation) {
			model.property("concept", new RefProperty("#/definitions/ConceptCreate"));
		}
		return model;
	}
	
	@Override
	public ServiceCatalog save(ServiceCatalog serviceCatalog) {
		return ctx.getCatalogService().saveServiceCatalog(serviceCatalog);
	}
	
	@Override
	public void purge(ServiceCatalog serviceCatalog, RequestContext requestContext) throws ResponseException {
		if (serviceCatalog == null)
			return;
		ctx.getCatalogService().purgeServiceCatalog(serviceCatalog);
	}
	
	@Override
	protected NeedsPaging<ServiceCatalog> doGetAll(RequestContext context) throws ResponseException {
		return new NeedsPaging<ServiceCatalog>(ctx.getCatalogService().getAllCatalogServices(), context);
	}
	
}
