package tn.undefined.universalhaven.buisness;

import javax.ejb.Local;
import tn.undefined.universalhaven.entity.Mail;
import tn.undefined.universalhaven.enumerations.UserRole;
@Local 
public interface MailServiceLocal {
	public String getSubscribedUsers();
	public boolean contacterNous(Mail mail);
	public boolean deleteMail(Mail mail);
	public String sendMailPerSomthing(UserRole role, String country, String skill);
}
