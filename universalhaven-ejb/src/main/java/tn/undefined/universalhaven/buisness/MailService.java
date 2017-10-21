package tn.undefined.universalhaven.buisness; 

import java.util.List;

import javax.ejb.Stateless; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.Mail;
import tn.undefined.universalhaven.entity.Person;
import tn.undefined.universalhaven.entity.User; 
 
@Stateless 
public class MailService implements MailServiceLocal,MailServiceRemote{ 
  @PersistenceContext 
  private EntityManager em;

@Override
public String getSubscribedUsers() {
	TypedQuery<String> query =  em.createQuery("select email from User where subscribed =1",String.class);
	 String listString = "";
	  for (String s : query.getResultList())
	    { listString += s + ",";
	    }
	  listString = listString.substring(0, listString.length() - 1);
	return listString;
}

@Override
public boolean sendMailToIcrc(Mail mail) {
		    try { 
		      em.persist(mail); 
		      return true; 
		    } 
		    catch (Exception e) { 
		      e.printStackTrace(); 
		      return false; 
		    } 
} 


@Override
public boolean sendMailPerRole(Mail mail,String Role) {
	 try { 
	      em.persist(mail); 
	      return true; 
	    } 
	    catch (Exception e) { 
	      e.printStackTrace(); 
	      return false; 
	    } 
}

@Override
public boolean sendMailPerCountry(Mail mail,String country) {
	 try { 
	      em.persist(mail); 
	      return true; 
	    } 
	    catch (Exception e) { 
	      e.printStackTrace(); 
	      return false; 
	    } 
}

@Override
public boolean sendMailPerSkill(Mail mail,String skill) {
	 try { 
	      em.persist(mail); 
	      return true; 
	    } 
	    catch (Exception e) { 
	      e.printStackTrace(); 
	      return false; 
	    } 
} 
  
}
