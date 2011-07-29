package org.esupportail.formation.domain.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
	private long id;
    /**
	 * True for public Task.
	 */
    private boolean publicTask;
    /**
     * Task's title
     */
    @Column(nullable = false)
    private String title;
    /**
     * Task's descriptio,
     */
    private String description;

   
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
	}
	/* ***** Constructors ************ */
	public Task(long id, boolean publicTask, String title, String description,Date date) {
		super();
		this.id = id;
		this.publicTask = publicTask;
		this.title = title;
		this.description = description;
		this.date = date;
	}
	public Task(Task t) {
		super();
		this.id = t.getId();
		this.publicTask = t.isPublicTask();
		this.title = t.getTitle();
		this.description = t.getDescription();
		this.date = t.getDate();
	}
	public Task() {
		super();
	}
	/* ***************** Misc ******************* */
	
}
