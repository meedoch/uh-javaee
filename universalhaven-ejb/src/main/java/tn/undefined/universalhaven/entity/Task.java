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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import tn.undefined.universalhaven.enumerations.TaskPriority;
import tn.undefined.universalhaven.enumerations.TaskStatus;
@Entity
@XmlRootElement

public class Task{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	private Date startingDate= new Date();
	
	private Date deadLine;
	
	private Date endingDate;
	
	private String description;
	
	private TaskStatus status;
	
	private TaskPriority priority;
	
	
	
	@OneToMany(mappedBy="task")
	private List<TaskComment> comments;
	
	@ManyToOne 
	private User taskAssigner;
	
	@ManyToOne
	private User taskExecutor;
	
	@ManyToOne
	private Camp camp;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Enumerated(EnumType.STRING)
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	@Enumerated(EnumType.STRING)
	public TaskPriority getPriority() {
		return priority;
	}
	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}
	
	
	public List<TaskComment> getComments() {
		return comments;
	}
	public void setComments(List<TaskComment> comments) {
		this.comments = comments;
	}
	public User getTaskAssigner() {
		return taskAssigner;
	}
	public void setTaskAssigner(User taskAssigner) {
		this.taskAssigner = taskAssigner;
	}
	public User getTaskExecutor() {
		return taskExecutor;
	}
	public void setTaskExecutor(User taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	public Camp getCamp() {
		return camp;
	}
	public void setCamp(Camp camp) {
		this.camp = camp;
	}
	
	
	
	
	
}
