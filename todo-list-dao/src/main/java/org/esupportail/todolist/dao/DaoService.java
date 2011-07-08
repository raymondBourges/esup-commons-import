/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.dao;

import java.io.Serializable;
import java.util.List;

import org.esupportail.todolist.domain.beans.Task;
import org.esupportail.todolist.domain.beans.User;

/**
 * The DAO service interface.
 */
public interface DaoService extends Serializable {

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////
	
	/**
	 * @param id
	 * @return the User instance that corresponds to an id.
	 */
	User getUser(String id);

	/**
	 * @return the list of all the users.
	 */
	List<User> getUsers();

	/**
	 * Add a user.
	 * @param user
	 */
	void addUser(User user);

	/**
	 * Delete a user.
	 * @param user
	 */
	void deleteUser(User user);

	/**
	 * Update a user.
	 * @param user
	 */
	void updateUser(User user);
	/**
	 * Get all public task.
	 */
	public List<Task> getPublicTasks() ;
	/**
	 * Get all task.
	 */
	public List<Task> getTasks();
	/**
	 * Get task for a user.
	 * @param task
	 */
	public List<Task> getTasksForUser(User u);
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
}
