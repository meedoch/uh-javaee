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
  @POST 
  @Path("/send")
  @Consumes (MediaType.APPLICATION_JSON) 
  public Response SendMail(Camp camp){ 
	  try{
          String host ="smtp.gmail.com" ;
          String user = "he.flach.smok.c4@gmail.com";
          String pass = "momo******";
          String to = "mohamedamine.mhiri@esprit.tn";
          String from = "he.flach.smok.c4@gmail.com";
          String subject = "this number to activate your account.";
          String messageText = "3asselama";
          boolean sessionDebug = false;

          Properties props = System.getProperties();

          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.host", host);
          props.put("mail.smtp.port", "587");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.required", "true");

          //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
          Session mailSession = Session.getDefaultInstance(props, null);
          mailSession.setDebug(sessionDebug);
          Message msg = new MimeMessage(mailSession);
          msg.setFrom(new InternetAddress(from));
          InternetAddress[] address = {new InternetAddress(to)};
          msg.setRecipients(Message.RecipientType.TO, address);
          msg.setSubject(subject); msg.setSentDate(new Date());
          msg.setText(messageText);

         Transport transport=mailSession.getTransport("smtp");
         transport.connect(host, user, pass);
         transport.sendMessage(msg, msg.getAllRecipients());
         transport.close();
         System.out.println("message send successfully");
         return Response.ok().entity("Camp added").build(); 
      }catch(Exception ex)
      {
          System.out.println(ex);
          return Response.status(Status.NOT_ACCEPTABLE).entity("Camp Not added").build();
      }

     
  } 



}
