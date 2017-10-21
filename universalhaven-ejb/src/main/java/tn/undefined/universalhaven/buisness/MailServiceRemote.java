package tn.undefined.universalhaven.buisness;
import java.util.List;

import javax.ejb.Remote;

import tn.undefined.universalhaven.entity.Mail;
import tn.undefined.universalhaven.entity.Person;
import tn.undefined.universalhaven.entity.User; 
@Remote 
public interface MailServiceRemote {
	public String getSubscribedUsers();
	public boolean sendMailToIcrc(Mail mail);
	public boolean sendMailPerRole(Mail mail,String role);
	public boolean sendMailPerCountry(Mail mail,String country);
	public boolean sendMailPerSkill(Mail mail,String skill);
}