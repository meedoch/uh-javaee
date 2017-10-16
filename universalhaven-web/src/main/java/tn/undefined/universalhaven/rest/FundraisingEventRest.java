package tn.undefined.universalhaven.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.undefined.universalhaven.service.FundraisingEventServiceLocal;
import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.*;
/*http://localhost:18080/universalhaven-web/rest/fundraisingEvent/*/
@Path("fundraisingEvent")
public class FundraisingEventRest {
	
	@EJB
	FundraisingEventServiceLocal fundraisingEventService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FundraisingEventDto> getAll(){
		return fundraisingEventService.listActiveEventsDto();
		
	}
	@Path("stat")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Long> getCountEventPerCamp(){
		return fundraisingEventService.getEventCountByCountry();
	}
	@Path("user/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FundraisingEventDto> getEventByUser(@PathParam("id")long idUser){
		return fundraisingEventService.listEventsByUserDto(idUser);
	}
	@Path("{d}")
	@POST
	@Consumes("application/xml")
	public Response ajouterEmploye(@PathParam("d")String description) {
		FundraisingEvent event =new FundraisingEvent();
		event.setDescription(description);
		fundraisingEventService.startEvent(event);
			return Response.status(Status.OK).build();
		
	}
	

}
