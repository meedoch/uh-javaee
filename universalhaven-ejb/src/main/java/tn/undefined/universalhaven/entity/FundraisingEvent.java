package tn.undefined.universalhaven.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tn.undefined.universalhaven.enumerations.Urgency;

@Entity

public class FundraisingEvent {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	private String description;
	
	private double goal;
	
	private Date publishDate= new Date();
	
	private Urgency urgency ;
	
	private Date finishingDate= null;
	
	private String imagePath;
	
	@OneToMany(mappedBy="fundraisingEvent")
	private List<Donation> donations;
	
	@ManyToOne
	private User publisher;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getGoal() {
		return goal;
	}
	public void setGoal(double goal) {
		this.goal = goal;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	@Enumerated(EnumType.STRING)
	public Urgency getUrgency() {
		return urgency;
	}
	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}
	public Date getFinishingDate() {
		return finishingDate;
	}
	public void setFinishingDate(Date finishingDate) {
		this.finishingDate = finishingDate;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	public List<Donation> getDonations() {
		return donations;
	}
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}	
