package tn.undefined.universalhaven.resources; 
 
import javax.ejb.EJB; 
import javax.faces.bean.RequestScoped; 
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import tn.undefined.universalhaven.buisness.CampServiceLocal; 
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
  @Path("/findallcamps")
  @Produces(MediaType.APPLICATION_JSON) 
  public List<Camp> getallcamps(){ 
    List<Camp> camps = new ArrayList<>(); 
    camps.addAll(serviceCamp.ListCamp());
    return camps; 
  } 
  
  
  @GET 
  @Path("/findbycountry")
  @Produces(MediaType.APPLICATION_JSON) 
  public Map<String,List<Camp>> getallpercountry(){ 
	  Map<String,List<Camp>>  camps ;
	  camps = (serviceCamp.ListCampPerCountry());
	  if(camps.isEmpty()==false){
		  System.out.println("is empty");
	  } 
    return camps; 
  } 
  @GET 
  @Path("/countcamps")
  @Produces(MediaType.APPLICATION_JSON) 
  public  Map<String, Long>  countAllCampsPerCountry(){ 
	  Map<String, Long>   camps ;
	  camps = (serviceCamp.CountCampPerCountry());
	  if(camps.isEmpty()==false){
		  System.out.println("is empty");
	  } 
    return camps; 
  } 
  @GET 
  @Produces(MediaType.APPLICATION_JSON) 
  public List<Camp> getall(){ 
    List<Camp> camps = new ArrayList<>(); 
    camps.add(new Camp()); 
    return camps; 
  } 
  @POST 
  @Path("/create")
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
  	@Path("/disbandcamps")
   @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
	public Response disbandCamps(@FormParam(value= "id")long campId)
	{
	  if (serviceCamp.disbandCamp(campId)){ 
	      return Response.ok().entity("Camp modified").build(); 
	    } 
	    else{ 
	      return Response.status(Status.NOT_ACCEPTABLE).entity("Camp no").build(); 
	    } 
   
}
  	@PUT
   @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
  	@Path("/updatecamp")
	public Response UpdateCamps(Camp camp)
	{
  		if (serviceCamp.updateCamp(camp)){ 
  	      return Response.ok().entity("Camp added").build(); 
  	    } 
  	    else{ 
  	      return Response.status(Status.NOT_ACCEPTABLE).entity("Camp Not added").build(); 
  	    } 
   
}


}
