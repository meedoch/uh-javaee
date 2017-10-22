package tn.undefined.universalhaven.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import tn.undefined.universalhaven.enumerations.UserRole;

@Entity
@XmlRootElement
public class User extends Person{
	private String email;
	private String address;
	private String login;
	private String password;
	private Date subscriptionDate;
	private String skills;
	private String profession;
	private String motivation;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	private boolean subscribed;
	private String token;
	@XmlTransient
	@OneToMany(mappedBy="publisher", fetch = FetchType.LAZY)
	private List<FundraisingEvent> fundraisingEvents;
	// Tasks li lezem ya3melhom
	@XmlTransient
	@OneToMany(mappedBy="taskExecutor")
	private List<Task> tasksToDo;
	
	// Tasks li 3tahom 
	@XmlTransient
	@OneToMany(mappedBy="taskAssigner")
	private List<Task> assignedTasks;
	
	@OneToOne(mappedBy="campManager")
	@XmlTransient
	private Camp managedCamp;
	
	@ManyToOne
	@XmlTransient
	private Camp assignedCamp;
	
	
	public User() {
		super();
	}
	
	
	public User(Long id, String name, String surname, Date birthDate, String country, String gender) {
		super(id, name, surname, birthDate, country, gender);
		// TODO Auto-generated constructor stub
	}


	public User(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Date getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	
	@Enumerated(EnumType.STRING)
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public boolean isSubscribed() {
		return subscribed;
	}
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@XmlTransient
	public List<FundraisingEvent> getFundraisingEvents() {
		return fundraisingEvents;
	}
	public void setFundraisingEvents(List<FundraisingEvent> fundraisingEvents) {
		this.fundraisingEvents = fundraisingEvents;
	}
	@XmlTransient
	public List<Task> getTasksToDo() {
		return tasksToDo;
	}
	public void setTasksToDo(List<Task> tasksToDo) {
		this.tasksToDo = tasksToDo;
	}
	@XmlTransient
	public List<Task> getAssignedTasks() {
		return assignedTasks;
	}
	public void setAssignedTasks(List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}
	@XmlTransient
	public Camp getAssignedCamp() {
		return assignedCamp;
	}
	public void setAssignedCamp(Camp assignedCamp) {
		this.assignedCamp = assignedCamp;
	}
	

	
	
	
	
	
	

	
	
	
}
