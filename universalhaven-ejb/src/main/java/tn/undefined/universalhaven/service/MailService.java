package tn.undefined.universalhaven.service; 

import java.util.List;

import javax.ejb.Stateless; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;
import tn.undefined.universalhaven.entity.User; 
 
@Stateless 
public class MailService implements MailServiceLocal,MailServiceRemote{ 
  @PersistenceContext 
  private EntityManager em;

@Override
public List<User> getSubscribedUsers() {
	// TODO Auto-generated method stub
	return null;
} 
  
}
