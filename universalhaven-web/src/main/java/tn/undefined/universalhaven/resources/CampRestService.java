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
import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.enumerations.UserRole;
import tn.undefined.universalhaven.jwt.JWTTokenNeeded;
import tn.undefined.universalhaven.buisness.CampServiceLocal; 
import java.util.*;
@Path("camp") 
@RequestScoped 
public class CampRestService { 
   
  @EJB 
  CampServiceLocal serviceCamp; 
   
   
  @GET 
  @Path("/findallcamps")
  @Produces(MediaType.APPLICATION_JSON) 
  @JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
  public Response getallcamps(){ 
    List<Camp> camps = new ArrayList<>(); 
    
    camps.addAll(serviceCamp.ListCamp());
    if (camps.isEmpty()){
    	return Response.status(Status.NO_CONTENT).build();
    }
    return Response.ok(camps).build();
  } 
  
  
  @GET 
  @Path("/findbycountry")
  @Produces(MediaType.APPLICATION_JSON) 
  @JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
  public Response getallpercountry(){ 
	  Map<String,List<Camp>>  camps ;
	  camps = (serviceCamp.ListCampPerCountry());
    return Response.status(Status.CREATED).entity(camps).build();  
  } 
  @GET 
  @Path("/countcamps")
  @Produces(MediaType.APPLICATION_JSON) 
  @JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
  public  Response  countAllCampsPerCountry(){ 
	  Map<String, Long>   camps ;
	  camps = (serviceCamp.CountCampPerCountry());
	  if(camps.isEmpty()==false){
		  System.out.println("is empty");
	  } 
	  return Response.ok(camps).build(); 
  } 
  @GET 
  @Produces(MediaType.APPLICATION_JSON) 
  public List<Camp> getall(){ 
    List<Camp> camps = new ArrayList<>(); 
    camps.add(new Camp()); 
    return camps; 
  } 
  @POST 
  @Consumes (MediaType.APPLICATION_JSON) 
  @JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
  public Response create(Camp camp){ 
     
    if (serviceCamp.CreateCamp(camp)){ 
      return Response.status(Status.CREATED).entity("Camp added").build(); 
    } 
    else{ 
      return Response.status(Status.NOT_ACCEPTABLE).entity("Camp Not added").build(); 
    } 
     
  } 
  	@PUT
  	@Path("/disbandcamps")
   @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
  	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response disbandCamps(@FormParam(value= "id")long campId)
	{
	  if (serviceCamp.disbandCamp(campId)){ 
	      return Response.ok().entity("Camp modified").build(); 
	    } 
	    else{ 
	      return Response.status(Status.NOT_MODIFIED).entity("Camp no").build(); 
	    } 
   
}
  	@PUT
   @Consumes (MediaType.APPLICATION_JSON)
  	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response UpdateCamps(Camp camp)
	{
  		if (serviceCamp.updateCamp(camp)){ 
  	      return Response.ok().entity("Camp edited").build(); 
  	    } 
  	    else{ 
  	      return Response.status(Status.NOT_MODIFIED).entity("Camp Not edited").build(); 
  	    } 
   
}


}
