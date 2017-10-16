package tn.undefined.universalhaven.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Mail implements Serializable{
	private String subject;
	private String password;
	private String content;
	
	private String recipientMail;
	private String senderMail;
	private String MailPassword;
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getMailPassword() {
		return MailPassword;
	}

	public void setMailPassword(String mailPassword) {
		MailPassword = mailPassword;
	}

	public String getSubject() {
		return subject;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public void setSubject(String subject) {
		subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		content = content;
	}

	public String getRecipientMail() {
		return recipientMail;
	}

	public void setRecipientMail(String recipientMail) {
		this.recipientMail = recipientMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
