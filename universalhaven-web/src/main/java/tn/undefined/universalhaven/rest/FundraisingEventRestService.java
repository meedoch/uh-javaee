package tn.undefined.universalhaven.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import tn.undefined.universalhaven.service.FundraisingEventServiceLocal;
import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.*;
/*http://localhost:18080/universalhaven-web/rest/fundraisingEvent/*/
@Path("fundraisingEvent")
public class FundraisingEventRestService {
	
	@EJB
	FundraisingEventServiceLocal fundraisingEventService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(){
		if(fundraisingEventService.listActiveEventsDto()==null)
			return null;
		return Response.status(Status.OK).entity(fundraisingEventService.listActiveEventsDto()).build();
	}	
	//http://localhost:18080/universalhaven-web/rest/fundraisingEvent/stat	
	
	@Path("stat")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountEventPerCamp(){
		if(fundraisingEventService.getEventCountByCountry()==null)
			return null;
		return Response.status(Status.OK).entity(fundraisingEventService.getEventCountByCountry()).build();
		
	}
	/*@Path("user/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FundraisingEventDto> getEventByUser(@PathParam("id")long idUser){
		return fundraisingEventService.listEventsByUserDto(idUser);
	}*/
	/*{"id":1}*/
	@Path("user")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventByUser(User user){
		if(fundraisingEventService.listEventsByUserDto(user)==null)
			return null;
		return Response.status(Status.ACCEPTED).entity(fundraisingEventService.listEventsByUserDto(user)).build();
		
	}
	/*{"title":"ddd","description":"eeee","goal":500,"imagePath":"aa.png","state":"In progress",
	  "urgency":"HIGH","publisher":1,"camp":2}*/
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response ajouterEmploye(FundraisingEvent event) {
		fundraisingEventService.startEvent(event);
		return Response.status(Status.CREATED).encoding("ok").build();
		
	}
	//http://localhost:18080/universalhaven-web/rest/fundraisingEvent/avgCompletionEvent
	@Path("avgCompletionEvent")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvgCompletionEvent(){
		if(fundraisingEventService.getAverageCompletionDate()==null)
			return null;
		return Response.status(Status.OK).entity(fundraisingEventService.getAverageCompletionDate()).build();
		
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEvent(FundraisingEvent event) {
		fundraisingEventService.updateEvent(event);
		return Response.status(Status.ACCEPTED).encoding("ok").build();
		
	}

    @GET  
    @Path("pdf")  
    @Produces("text/plain")  
    public Response getFile() {  
        File file = new File("C:\\Users\\Dell\\Desktop\\4TWIN\\DemoFile.txt");  
        ResponseBuilder response = Response.ok((Object) file);  
        response.header("Content-Disposition","attachment; filename=DisplayName-DemoFile.txt");  
        return response.build();  
    }  

}
