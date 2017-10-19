package tn.undefined.universalhaven.buisness; 
 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
  public boolean disbandCamp(long campid) { 
	 
	  try { 
		  Camp camp = em.find(Camp.class, campid);
		  camp.setClosingDate(new Date());;
			em.merge(camp);
			return true; 
	    } 
	    catch (Exception e) { 
	      e.printStackTrace(); 
	      return false;  
	    } 
  	}
@Override
public List<Camp> ListCamp() {
	TypedQuery<Camp> query =  em.createQuery("select c from Camp c where closingDate is NULL",Camp.class);
	return query.getResultList();
}
@Override
public Map<String,List<Camp>> ListCampPerCountry() {
	Map<String,List<Camp>> maaap = new HashMap<>() ; ;
	TypedQuery<String> query =  em.createQuery("select DISTINCT c.country from Camp c group by c.country ",String.class);
	for (String temp : query.getResultList()) {
		TypedQuery<Camp> camp =  em.createQuery("select c from Camp c where country = :pay ",Camp.class);
		camp.setParameter("pay", temp);
		maaap.put(temp, camp.getResultList());

	}

	return maaap;
}
@Override
public Map<String, Long> CountCampPerCountry() {
	Map<String,Long> maaap = new HashMap<>() ;
	Query query =  em.createQuery("select count(c),c.country from Camp c group by c.country ");
	List<Object[]> results = query.getResultList();
	for (Object[] temp : results) {
		long count = (Long) temp[0];
		String country=  (String) temp[1];
		maaap.put(country, count);
	}
	return maaap;
}
@Override
public boolean updateCamp(long id,String name) {
	  try { 
		  Camp camp = em.find(Camp.class, id);
		  camp.setName(name);
			em.merge(camp);
			return true; 
	    } 
	    catch (Exception e) { 
	      e.printStackTrace(); 
	      return false;  
	    } 
}
  
  } 
