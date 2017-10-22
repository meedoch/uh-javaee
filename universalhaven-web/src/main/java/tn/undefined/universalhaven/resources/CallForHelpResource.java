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

import tn.undefined.universalhaven.buisness.CallForHelpServiceLocal;
import tn.undefined.universalhaven.entity.CallForHelp;


@Path("callforhelp")
@RequestScoped
@ManagedBean
public class CallForHelpResource {
	@EJB
	private CallForHelpServiceLocal callForHelpServiceLocal;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCallForHelpResource(CallForHelp callForHelp) {
		if (callForHelpServiceLocal.startCallForHelp(callForHelp))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listActiveCallForHelpEventsResource() {
		return Response.ok(callForHelpServiceLocal.listEvents()).build();
	}

	@GET
	@Path("active")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listCallForHelpEventsResource() {
		return Response.ok(callForHelpServiceLocal.listActiveEvents()).build();
	}

	@GET
	@Path("{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCallForHelpByNameResource(@PathParam("title") String title) {
		return Response.ok(callForHelpServiceLocal.findCallForHelpByTitle(title)).build();
	}

	@GET
	@Path("description/{str}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCallForHelpByCriteriaResource(@PathParam("str") String description) {
		return Response.ok(callForHelpServiceLocal.findCallForHelpByDescription(description)).build();
		}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyCallForHelpResource(CallForHelp callForHelp) {
		if (callForHelpServiceLocal.modifyCallForHelp(callForHelp))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@DELETE
	public Response removeCallForHelpResource(CallForHelp callForHelp) {
		if (callForHelpServiceLocal.endCallForHelp(callForHelp))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
	}
}
