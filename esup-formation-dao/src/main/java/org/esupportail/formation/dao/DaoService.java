/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.formation.dao;

import java.io.Serializable;
import java.util.List;

import org.esupportail.formation.domain.beans.Task;
import org.esupportail.formation.domain.beans.User;

/**
 * The DAO service interface.
 */
public interface DaoService extends Serializable {

	/**
	 * Get all public task.
	 */
	public List<Task> getPublicTasks() ;
	/**
	 * Get all task.
	 */
	public List<Task> getTasks();
	
	/**
	 * Add a task.
	 * @param task
	 */
	void addTask(Task task);

	/**
	 * Delete a task.
	 * @param task
	 */
	void deleteTask(Task task);

	/**
	 * Update a task.
	 * @param task
	 */
	Task updateTask(Task task);
	/**
	 * @param id
	 * @return the Task instance that corresponds to an id.
	 */
	Task getTask(long id);
	
	public List<Task> getTasksForUser(User u);
	public List<Task> get10LastTasksForUser(User u);
	
	public List<User> getUsers();

	void addUser(User user);

	void deleteUser(User user);

	User updateUser(User user);

	User getUser(long id);
	User getUser(String login);
	
}
