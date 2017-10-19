package tn.undefined.universalhaven.service; 
 
import java.util.List; 
 
import javax.ejb.Local; 
 
import tn.undefined.universalhaven.entity.Camp; 
@Local 
public interface CampServiceLocal { 
  public boolean CreateCamp(Camp camp); 
  public boolean disbandCamp(Camp camp); 
  //public boolean ListCamp(); 
  //public List<Camp> ListCampPerCountry(); 
  //public boolean updateCamp(long id); 
} 
