package tn.undefined.universalhaven.buisness; 

import java.util.List;

import javax.ejb.Stateless; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
  
}
