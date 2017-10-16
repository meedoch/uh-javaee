package tn.undefined.universalhaven.rest; 
 
import java.util.ArrayList; 
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB; 
import javax.faces.bean.RequestScoped; 
import javax.ws.rs.Consumes; 
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Response; 
import javax.ws.rs.core.Response.Status; 
import javax.ws.rs.PathParam;
import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.Mail;
import tn.undefined.universalhaven.service.CampServiceLocal; 
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

@Path("camp") 
@RequestScoped 
public class CampRestService { 
   
  @EJB 
  CampServiceLocal serviceCamp; 
   
   
  @GET 
  @Produces(MediaType.APPLICATION_JSON) 
  public List<Camp> getall(){ 
    List<Camp> camps = new ArrayList<>(); 
    camps.add(new Camp()); 
    return camps; 
  } 
   
  @POST 
  @Consumes (MediaType.APPLICATION_JSON) 
  public Response create(Camp camp){ 
     
    if (serviceCamp.CreateCamp(camp)){ 
      return Response.ok().entity("Camp added").build(); 
    } 
    else{ 
      return Response.status(Status.NOT_ACCEPTABLE).entity("Camp Not added").build(); 
    } 
     
  } 
  @PUT
  @Path("{id}")
  @Consumes (MediaType.APPLICATION_JSON)
	public Response modifierEmploye(Camp camp)
	{
	  if (serviceCamp.disbandCamp(camp)){ 
	      return Response.ok().entity("Camp modified").build(); 
	    } 
	    else{ 
	      return Response.status(Status.NOT_ACCEPTABLE).entity("Camp no").build(); 
	    } 
   
}
 


}
