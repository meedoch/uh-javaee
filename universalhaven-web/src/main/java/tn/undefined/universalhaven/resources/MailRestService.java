package tn.undefined.universalhaven.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.Mail;
import tn.undefined.universalhaven.entity.Person;
import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.buisness.CampServiceLocal;
import tn.undefined.universalhaven.buisness.MailServiceLocal;
@Path("mail") 
@RequestScoped 
public class MailRestService {
	@EJB 
	  MailServiceLocal serviceMail; 
	 @POST 
	  @Path("/send")
	  @Consumes (MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.APPLICATION_JSON) 
	  public Response SendMail(Mail mail){ 
		
		  try{
	          String host ="smtp.gmail.com" ;
	          String user = mail.getSenderMail();
	          String pass = mail.getMailPassword();
	          String to = "customercare@universalhaven.com";
	          String from = mail.getSenderMail();
	          String subject = mail.getSubject();
	          String messageText = mail.getContent();
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
	         return Response.ok().entity("message send successfully").build(); 
	      }catch(Exception ex)
	      {
	          System.out.println(ex);
	          return Response.status(Status.NOT_ACCEPTABLE).entity("problem of sending the message").build();
	      }
		
		  
		 
	     
	  } 
	  @POST 
	  @Path("/newsletter")
	  @Consumes (MediaType.APPLICATION_JSON) 
	  public Response SendNewsLetter(Mail mail){ 
		  //String to ="";
		  //users += serviceMail.getSubscribedUsers();
		 
		  
		  try{
	          String host ="smtp.gmail.com" ;
	          String user = "he.flach.smok.c4@gmail.com";
	          String pass = "mo********";
	          //String to = "mohamedamine.mhiri@esprit.tn,plopcava@hotmail.fr";
	          String to = serviceMail.getSubscribedUsers();
	          
	          
	          String from = "universalhaven@noreply.com";
	          String subject = mail.getSubject();
	          String messageText = mail.getContent();
	          boolean sessionDebug = false;
	          String[] recipientList = to.split(",");
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
	          InternetAddress[] address = new InternetAddress[recipientList.length];
	          int counter = 0;
	          for (String recipient : recipientList) {
	        	  address[counter] = new InternetAddress(recipient.trim());
	        	    counter++;
	        	}
	          msg.setRecipients(Message.RecipientType.TO, address);
	          msg.setSubject(subject); msg.setSentDate(new Date());
	          msg.setText(messageText);

	         Transport transport=mailSession.getTransport("smtp");
	         transport.connect(host, user, pass);
	         transport.sendMessage(msg, msg.getAllRecipients());
	         transport.close();
	         System.out.println("NewsLetter sended successfully");
	         return Response.ok().entity("NewsLetter sended successfully").build(); 
	      }catch(Exception ex)
	      {
	          System.out.println(ex);
	          return Response.status(Status.NOT_ACCEPTABLE).entity("Problem of sending the NewsLetter").build();
	      }
		    

	     
	  }
}
