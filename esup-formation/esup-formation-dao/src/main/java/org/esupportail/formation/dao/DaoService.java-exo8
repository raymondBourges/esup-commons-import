/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.formation.dao;

import java.io.Serializable;
import java.util.List;

import org.esupportail.formation.domain.beans.Task;

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
}
