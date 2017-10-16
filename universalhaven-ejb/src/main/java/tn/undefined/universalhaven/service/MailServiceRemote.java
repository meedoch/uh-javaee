package tn.undefined.universalhaven.service;
import java.util.List;

import javax.ejb.Remote;

import tn.undefined.universalhaven.entity.User; 
@Remote 
public interface MailServiceRemote {
	public List<User> getSubscribedUsers();
}