package tn.undefined.universalhaven.buisness;
import java.util.List;

import javax.ejb.Remote;

import tn.undefined.universalhaven.entity.Person;
import tn.undefined.universalhaven.entity.User; 
@Remote 
public interface MailServiceRemote {
	public String getSubscribedUsers();
}