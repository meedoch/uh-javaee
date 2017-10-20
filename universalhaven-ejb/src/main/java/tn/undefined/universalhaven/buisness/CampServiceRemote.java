package tn.undefined.universalhaven.buisness; 
 
import java.util.List;
import java.util.Map;

import javax.ejb.Remote; 
 
import tn.undefined.universalhaven.entity.Camp; 
@Remote 
public interface CampServiceRemote { 
  public boolean CreateCamp(Camp camp); 
  public boolean disbandCamp(long camp); 
  public List<Camp>  ListCamp(); 
  public Map<String,List<Camp>> ListCampPerCountry();
  public Map<String,Long> CountCampPerCountry();
  public boolean updateCamp(Camp camp); 
 
} 
