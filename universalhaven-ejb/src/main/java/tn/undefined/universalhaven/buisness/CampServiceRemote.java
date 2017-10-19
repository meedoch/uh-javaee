package tn.undefined.universalhaven.buisness; 
 
import java.util.List;

import javax.ejb.Remote; 
 
import tn.undefined.universalhaven.entity.Camp; 
@Remote 
public interface CampServiceRemote { 
  public boolean CreateCamp(Camp camp); 
  public boolean disbandCamp(Camp camp); 
  //public boolean ListCamp(); 
  //public List<Camp> ListCampPerCountry(); 
  //public boolean updateCamp(long id); 
 
} 
