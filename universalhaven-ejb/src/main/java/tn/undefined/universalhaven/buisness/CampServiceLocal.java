package tn.undefined.universalhaven.buisness; 
 
import java.util.List;
import java.util.Map;

import javax.ejb.Local; 
 
import tn.undefined.universalhaven.entity.Camp; 
@Local 
public interface CampServiceLocal { 
  public boolean CreateCamp(Camp camp); 
  public boolean disbandCamp(long camp); 
  public List<Camp>  ListCamp(); 
  public Map<String,List<Camp>> ListCampPerCountry(); 
  public Map<String,Long> CountCampPerCountry();
  public boolean updateCamp(long id,String name); 
} 
