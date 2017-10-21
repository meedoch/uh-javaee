package tn.undefined.universalhaven.buisness;
import java.util.List;

import javax.ejb.Local;

import tn.undefined.universalhaven.entity.Mail;
import tn.undefined.universalhaven.entity.User; 
@Local 
public interface MailServiceLocal {
	public String getSubscribedUsers();
	public boolean sendMailToIcrc(Mail mail);
	public boolean sendMailPerRole(Mail mail,String role);
	public boolean sendMailPerCountry(Mail mail,String country);
	public boolean sendMailPerSkill(Mail mail,String skill);
}
