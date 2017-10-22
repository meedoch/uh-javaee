package tn.undefined.universalhaven.resources;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.undefined.universalhaven.buisness.ResourceServiceLocal;
import tn.undefined.universalhaven.entity.Resource;

@Path("resource")
@RequestScoped
@ManagedBean
public class ResourcesResource {

	@EJB
	private ResourceServiceLocal resourceServiceLocal;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addResourcesResource(Resource resource) {
		if (resourceServiceLocal.addResource(resource))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyResourcesResource(Resource resource) {
		if (resourceServiceLocal.depositResource(resource))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCampResourcesResource(@PathParam("id") long id) {

		return Response.ok(resourceServiceLocal.getCampResources(id)).build();
	}

	@DELETE
	public Response removeReourcesResource(Resource resource) {
		if (resourceServiceLocal.removeResource(resource))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
	}
	/*
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Path("/update/{param1}/") public Response
	 * updateGallery(@PathParam("param1") int id, Schedule sch) {
	 * galleryService.AffectPlanToGallery(id ,sch); return
	 * Response.noContent().build(); }
	 */
}
