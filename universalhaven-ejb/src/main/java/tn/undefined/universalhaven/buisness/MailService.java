package tn.undefined.universalhaven.buisness; 

import javax.ejb.Stateless; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.undefined.universalhaven.entity.Mail;
import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.enumerations.UserRole;

 
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
public boolean contacterNous(Mail mail) {
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
public String sendMailPerSomthing(UserRole role, String country, String skill) {
	User user = new User();
	user.setRole(role);
	String roles =  Integer.toString(user.getRole().ordinal()) ;
	System.out.println(roles);
	TypedQuery<String> query= null;
	String listString = "";
	if(role != null){
		 query =  em.createQuery("select email from User where Role =:role",String.class);
		 query.setParameter("role", roles);
	}
	if(country != null){
		 query =  em.createQuery("select email from User where country =:country",String.class);
		 query.setParameter("country", country);
	}
	if(skill != null){
		 query =  em.createQuery("select email from User where skills =:skill",String.class);
		 query.setParameter("skill", skill);
	}
	 
	  for (String s : query.getResultList())
	    { listString += s + ",";
	    }
	  listString = listString.substring(0, listString.length() - 1);
	return listString;
}

@Override
public boolean deleteMail(Mail mail) {
		  try {  
			  Mail mails = em.find(Mail.class, mail.getId());
				em.remove(mails);
				return true; 
		    } 
		    catch (Exception e) { 
		      e.printStackTrace(); 
		      return false;  
		    } 
	} 
  
}
