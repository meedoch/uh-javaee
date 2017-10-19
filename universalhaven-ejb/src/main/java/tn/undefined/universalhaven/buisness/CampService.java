package tn.undefined.universalhaven.service; 
 
import java.util.Date;

import javax.ejb.Stateless; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;


import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.Mail; 
 
@Stateless 
public class CampService implements CampServiceLocal,CampServiceRemote{ 
  @PersistenceContext 
  private EntityManager em; 
  @Override 
  public boolean CreateCamp(Camp camp) { 
    try { 
      em.persist(camp); 
      return true; 
    } 
    catch (Exception e) { 
      e.printStackTrace(); 
      return false; 
    } 
  } 
  @Override 
  public boolean disbandCamp(Camp camps) { 
	 
	  try { 
		  Camp camp = em.find(Camp.class, camps.getId());
		  camp.setClosingDate(new Date());;
			em.merge(camp);
			return true; 
	    } 
	    catch (Exception e) { 
	      e.printStackTrace(); 
	      return false;  
	    } 
  	}
  
  } 
