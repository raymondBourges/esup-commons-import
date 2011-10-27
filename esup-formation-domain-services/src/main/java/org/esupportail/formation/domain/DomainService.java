/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.domain;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.esupportail.formation.domain.beans.Task;
import org.esupportail.formation.domain.beans.User;

/**
 * @author Yves Deschamps (Universite de Lille 1) - 2010
 * 
 */
@WebService
@Path("/JSONServices/")
@Produces("application/json")
public interface DomainService extends Serializable {
	
	/**
	 * @param uid
	 * @return a user.
	 */
	@GET
	@Path("/user/{login}")
	public User getUser(@PathParam("login") String uid);
	@GET
	@Path("/tasks")
	public List<Task> getTasks();
	public List<Task> getPublicTasks();
	public Task getTask(long id);
	void addTask(Task task);
	public void deleteTask(Task task);
	public void updateTask(Task task);
	public List<Task> getTasksForUser(User user);
	public List<Task> getLastTasksForUser(User user,int limit);
	@GET
	@Path("/tasks/{login}")
	public List<Task> get10LastTasksForUserString(@PathParam("login") String uid);
	public void addUser(User user);
	public void deleteUser(User user);
	public List<User> getUsers();
}
