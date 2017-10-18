package tn.undefined.universalhaven.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement

public class Camp implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String latLng;
	
	private String address;
	
	private double superficy;
	
	private boolean electricity;
	
	private boolean water;
	
	private int capacity;
	
	private int occupancy;
	
	private double budget;
	
	private Date creationDate= new Date();
	
	private Date closingDate;
	
	@OneToMany(mappedBy="camp")
	private List<Suggestion> suggestions;
	
	@OneToMany(mappedBy="camp")
	private List<Refugee> refugees;
	
	@OneToMany(mappedBy="camp")
	private List<Resource> resources;
	
	@OneToOne
	private User campManager;
	
	@ManyToOne
	private User campCreator;
	@OneToMany(mappedBy="assignedCamp")
	private List<User> campStaff;
	@OneToMany(mappedBy="camp")
	private List<CallForHelp> callForHelpEvents;
	
	@OneToMany(mappedBy="camp")
	private List<Task> campTasks;
	@OneToMany(mappedBy="camp", fetch = FetchType.LAZY)
	private List<FundraisingEvent> fundraisingEvents;
	public List<FundraisingEvent> getFundraisingEvents() {
		return fundraisingEvents;
	}
	public void setFundraisingEvents(List<FundraisingEvent> fundraisingEvents) {
		this.fundraisingEvents = fundraisingEvents;
	}
	
	public Camp() {
		super();
	}
	
	public Camp(long id) {
		super();
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatLng() {
		return latLng;
	}
	public void setLatLng(String latLng) {
		this.latLng = latLng;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getSuperficy() {
		return superficy;
	}
	public void setSuperficy(double superficy) {
		this.superficy = superficy;
	}
	public boolean isElectricity() {
		return electricity;
	}
	public void setElectricity(boolean electricity) {
		this.electricity = electricity;
	}
	public boolean isWater() {
		return water;
	}
	public void setWater(boolean water) {
		this.water = water;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	public List<Suggestion> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	public List<Refugee> getRefugees() {
		return refugees;
	}
	public void setRefugees(List<Refugee> refugees) {
		this.refugees = refugees;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	public User getCampManager() {
		return campManager;
	}
	public void setCampManager(User campManager) {
		this.campManager = campManager;
	}
	public User getCampCreator() {
		return campCreator;
	}
	public void setCampCreator(User campCreator) {
		this.campCreator = campCreator;
	}
	public List<User> getCampStaff() {
		return campStaff;
	}
	public void setCampStaff(List<User> campStaff) {
		this.campStaff = campStaff;
	}
	public List<CallForHelp> getCallForHelpEvents() {
		return callForHelpEvents;
	}
	public void setCallForHelpEvents(List<CallForHelp> callForHelpEvents) {
		this.callForHelpEvents = callForHelpEvents;
	}
	public List<Task> getCampTasks() {
		return campTasks;
	}
	public void setCampTasks(List<Task> campTasks) {
		this.campTasks = campTasks;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camp other = (Camp) obj;
		if (id != other.id)
			return false;
		return true;
	} 
	
	
	
	
	
}
