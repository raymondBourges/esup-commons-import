package org.esupportail.todolist.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.web.controllers.Resettable;

@Entity
@NamedQueries({
	@NamedQuery(
		    name="allTasks",
		    query="SELECT t FROM Task t"
		    ),
    @NamedQuery(
    name="publicTasks",
    query="SELECT t FROM Task t WHERE t.publicTask = true"
    ),   
    @NamedQuery(
    name="tasksForUser",
    query="SELECT t FROM Task t LEFT JOIN t.assignatedUsers u  WHERE t.owner.id = :userId or u.id = :userId"
    )   
})
public class Task implements Serializable, Resettable{

	/**
	 * The serialization id.
	 */
	@Embedded
	private static final long serialVersionUID = 5576556657584434915L;

	/**
	 * Task Id
	 */
	@Id
	@GeneratedValue
	@NotNull
	private long id;
    /**
	 * True for public Task.
	 */
    private boolean publicTask;
    /**
     * Task's title
     */
    @NotNull
    @Column(nullable = false)
    private String title;
    /**
     * Task's descriptio,
     */
    @NotNull
	@Size(max = 50, min = 1)
    private String description;
    /**
     * User who create the task's
     */
    @OneToOne
    private User owner;
    /**
     * List of users assignated to the task
     */
    @ManyToMany
    private List<User> assignatedUsers;
    /**
     * Dead line date to do the task
     */
    private Date date;
    /*
	 ******************* ACCESSORS ******************* */
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
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<User> getAssignatedUsers() {
		return assignatedUsers;
	}
	public List<User> getAssignatedUsersNotNull() {
		if (assignatedUsers==null)
			assignatedUsers= new ArrayList<User>();
		return assignatedUsers;
	}
	public void setAssignatedUsers(List<User> assignatedUsers) {
		this.assignatedUsers = assignatedUsers;
	}
	public boolean isPublicTask() {
		return publicTask;
	}
	public void setPublicTask(boolean publicTask) {
		this.publicTask = publicTask;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
	
	public void reset() {
		this.title=null;
		this.description=null;
		this.date=null;
		this.publicTask=false;
		this.id=0;
		this.owner=null;
		this.assignatedUsers=null;
	}
	/* ***** Constructors ************ */
	public Task(long id, boolean publicTask, String title, String description,
			User owner, List<User> assignatedUsers, Date date) {
		super();
		this.id = id;
		this.publicTask = publicTask;
		this.title = title;
		this.description = description;
		this.owner = owner;
		this.assignatedUsers = assignatedUsers;
		this.date = date;
	}
	public Task(Task t) {
		super();
		this.id = t.getId();
		this.publicTask = t.isPublicTask();
		this.title = t.getTitle();
		this.description = t.getDescription();
		this.owner = t.getOwner();
		this.assignatedUsers = t.getAssignatedUsers();
		this.date = t.getDate();
	}
	public Task() {
		super();
	}
	/* ***************** Misc ******************* */
	
}