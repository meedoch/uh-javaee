package tn.undefined.universalhaven.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.undefined.universalhaven.service.FundraisingEventServiceLocal;
import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.*;

@Path("fundraisingEvent")
public class FundraisingEventRest {
	
	@EJB
	FundraisingEventServiceLocal fundraisingEventService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FundraisingEventDto> getAll(){
		return fundraisingEventService.listActiveEventsDto();
		
	}
	

}
