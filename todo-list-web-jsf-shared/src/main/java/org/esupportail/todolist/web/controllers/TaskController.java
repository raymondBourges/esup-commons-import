/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.web.controllers;

import java.util.List;

import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.todolist.domain.beans.Task;
import org.esupportail.todolist.domain.beans.User;
import org.esupportail.todolist.services.application.TodoApplicationService;
import org.esupportail.todolist.web.beans.PublicTasksPaginator;
import org.esupportail.todolist.web.beans.TasksPaginator;
import org.esupportail.todolist.web.beans.UserTasksPaginator;


/**
 * A visual bean for the welcome page.
 */
public class TaskController extends AbstractContextAwareController {

	/*
	 ******************* PROPERTIES ******************** */

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -4445423987226841665L;

	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(this.getClass());

	
	/**
	 * Public Tasks paginator
	 */
	private PublicTasksPaginator publicTasksPaginator;
	private UserTasksPaginator userTasksPaginator;
	private TasksPaginator tasksPaginator;

	private Task taskToUpdate;
	
	private String anAssignatedUserId;
	private User assignatedUserToDelete;
	/*
	 ******************* INIT ******************** */

	/**
	 * Bean constructor.
	 */
	public TaskController() {
		super();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode();
	}

	/**
	 * @see org.esupportail.todolist.web.controllers.AbstractDomainAwareBean#reset()
	 */
	@Override
	public void reset() {
		super.reset();		
	}

	/**
	 * @see org.esupportail.todolist.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		Assert.notNull(this.publicTasksPaginator, "property tasksPaginator of class " 
				+ this.getClass().getName() + " can not be null");
		publicTasksPaginator.forceReload();
		Assert.notNull(this.userTasksPaginator, "property tasksPaginator of class " 
				+ this.getClass().getName() + " can not be null");
		userTasksPaginator.forceReload();
		Assert.notNull(this.tasksPaginator, "property tasksPaginator of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.taskToUpdate, "property userToUpdate of class " 
				+ this.getClass().getName() + " can not be null");
		tasksPaginator.forceReload();
	}
	

	/*
	 ******************* METHODS ******************** */
	@Override
	protected User getCurrentUser() {
		User u=super.getCurrentUser();
		if (u!=null && u.getDisplayName()==null){// si utilisateur authentifié mais pas identifié
			logger.debug("Dans getCurrentUser " + u.toString() + u.getId() + u.getDisplayName());
			User ubase=getDomainService().getUser(u.getId());
			if (ubase!=null){// si identifié en base
				u=ubase;
			}
			else if (((TodoApplicationService)getApplicationService()).isUseLdap()){// Si on utilise LDAP on cherche le user et on l'ajoute en base
				u=getDomainService().getUserFromLdap(u.getId());
				getDomainService().addUser(u);
			}
			userTasksPaginator.setUser(u);
			userTasksPaginator.forceReload();
		}
		return u;
	}
	
	
	public List<Task> getPublicTasks() {
		return publicTasksPaginator.getVisibleItems();
	}
	public List<Task> getUserTasks() {
		if (userTasksPaginator.getUser()==null){
			userTasksPaginator.setUser(getCurrentUser());
			userTasksPaginator.forceReload();
		}
		return userTasksPaginator.getVisibleItems();
	}
	public List<Task> getAllTasks() {
		return tasksPaginator.getVisibleItems();
	}
	
	public void addPublicTask(){
		Task newPublicTask=new Task();
		User u = getDomainService().getUser("anonymous");
		if (u==null){
			u=new User();
			u.setId("anonymous");
			u.setDisplayName("Anonymous User");
			getDomainService().addUser(u);
		}
		newPublicTask.setOwner(u);
		newPublicTask.setTitle("Tâche arbitraire");
		newPublicTask.setDescription("Tâche arbitraire publique créée avec juste un bouton");
		newPublicTask.setPublicTask(true);
		/*Assignate arbitrary users */
		/* User u1=getDomainService().getUser("cbissler");
		User u2=getDomainService().getUser("anonymous");
		newPublicTask.getAssignatedUsersNotNull().add(u1);
		newPublicTask.getAssignatedUsersNotNull().add(u2);*/
		getDomainService().addTask(newPublicTask);
		publicTasksPaginator.forceReload();
		
		addInfoMessage(null, "TODO_DEMO.FORM.PUBLICTASK.SUCESS");
	}
	
	/**
	 * Add a task
	 */
	public void addTask() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering addTask with taskToUpdate = " + taskToUpdate);
			logger.debug("title = " + taskToUpdate.getTitle());
			logger.debug("desc = " + taskToUpdate.getDescription());
		}
		Task newTask=new Task(taskToUpdate);
		newTask.setOwner(getCurrentUser());
		getDomainService().addTask(newTask);
		
		userTasksPaginator.forceReload();
		tasksPaginator.forceReload();
		publicTasksPaginator.forceReload();
		taskToUpdate.reset();
		addInfoMessage(null, "TODO_DEMO.TASK.SUCCESS.CREATE");
	}
	public void addAnAssignatedUser() {
		logger.debug("addAnAssignatedUser "+anAssignatedUserId);
		try {
			User u = getDomainService().getUser(this.anAssignatedUserId);
			if (u==null){
				u=new User();
				u.setId(anAssignatedUserId);
					
				if (((TodoApplicationService)getApplicationService()).isUseLdap()){// Si on utilise LDAP on cherche le user et on l'ajoute en base
					u=getDomainService().getUserFromLdap(u.getId());
				}
				getDomainService().addUser(u);
			}
			taskToUpdate.getAssignatedUsersNotNull().add(u);
			this.anAssignatedUserId="";
		} catch (UserNotFoundException e) {
			addErrorMessage(null, "TODO_DEMO.FORM.TASK.ASSIGNATEDUSER.ERROR.CREATE", anAssignatedUserId);
		}
		addInfoMessage(null, "TODO_DEMO.FORM.TASK.ASSIGNATEDUSER.SUCESS.CREATE");
	}
	
	/**
	 * Delete the user.
	 */
	public void deleteTask() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteTask with taskToUpdate = " + taskToUpdate);
		}
		getDomainService().deleteTask(taskToUpdate);
		userTasksPaginator.forceReload();
		tasksPaginator.forceReload();
		publicTasksPaginator.forceReload();
		taskToUpdate.reset();
		addInfoMessage(null, "TODO_DEMO.FORM.TASK.SUCESS.DELETED");
	}
	public void deleteAssignatedUser() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteAssignatedUser with assignatedUserToDelete = " + assignatedUserToDelete.getId());
		}
		taskToUpdate.getAssignatedUsers().remove(assignatedUserToDelete);
		getDomainService().addTask(taskToUpdate);
		addInfoMessage(null, "TODO_DEMO.FORM.TASK.ASSIGNATEDUSER.SUCESS.DELETED");
	}
	public void setTaskToDelete(final Task taskToDelete) {
		taskToUpdate=taskToDelete;
	}
	public void editTask() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering editTask with taskToUpdate = " + taskToUpdate.getTitle());
		}
	}
	public void updateTask() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteTask with updateTask = " + taskToUpdate.getTitle());
		}
		getDomainService().addTask(taskToUpdate);
		userTasksPaginator.forceReload();
		tasksPaginator.forceReload();
		publicTasksPaginator.forceReload();
		taskToUpdate.reset();
		addInfoMessage(null, "TODO_DEMO.FORM.TASK.SUCESS.UPDATED");
	}
	public void setTaskToEdit(final Task taskToEdit) {
		logger.debug("entering setTaskToEdit with taskToEdit = " + taskToEdit.getTitle());
		taskToUpdate=taskToEdit;
	}
	public String getPublicTaskWarn(){
		if (taskToUpdate.isPublicTask()){
			return getString("TODO_DEMO.TASK.PUBLICTASK.WARN");
		}
		else return null;
	}
	/*
	 ******************* Getters and setters ******************** */

	public PublicTasksPaginator getPublicTasksPaginator() {
		return publicTasksPaginator;
	}

	public void setPublicTasksPaginator(PublicTasksPaginator publicTasksPaginator) {
		this.publicTasksPaginator = publicTasksPaginator;
	}
	
	public UserTasksPaginator getUserTasksPaginator() {
		return userTasksPaginator;
	}

	public void setUserTasksPaginator(UserTasksPaginator userTasksPaginator) {
		this.userTasksPaginator = userTasksPaginator;
	}
	public TasksPaginator getTasksPaginator() {
		return tasksPaginator;
	}
	public void setTasksPaginator(TasksPaginator tasksPaginator) {
		this.tasksPaginator = tasksPaginator;
	}

	public Task getTaskToUpdate() {
		return taskToUpdate;
	}

	public void setTaskToUpdate(Task taskToUpdate) {
		this.taskToUpdate = taskToUpdate;
	}

	public String getAnAssignatedUserId() {
		return anAssignatedUserId;
	}

	public void setAnAssignatedUserId(String anAssignatedUserId) {
		logger.debug("setAnAssignatedUserId "+anAssignatedUserId);
		this.anAssignatedUserId = anAssignatedUserId;
	}

	public User getAssignatedUserToDelete() {
		return assignatedUserToDelete;
	}

	public void setAssignatedUserToDelete(User assignatedUserToDelete) {
		logger.debug("setAssignatedUserToDelete "+assignatedUserToDelete.getId());
		this.assignatedUserToDelete = assignatedUserToDelete;
	}	
}
