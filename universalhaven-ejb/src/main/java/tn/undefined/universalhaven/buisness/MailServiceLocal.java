package tn.undefined.universalhaven.buisness;
import java.util.List;

import javax.ejb.Local;
import tn.undefined.universalhaven.entity.User; 
@Local 
public interface MailServiceLocal {
	public String getSubscribedUsers();
}
