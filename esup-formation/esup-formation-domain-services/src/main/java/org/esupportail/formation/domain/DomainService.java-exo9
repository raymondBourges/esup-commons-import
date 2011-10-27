/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.domain;

import java.io.Serializable;
import java.util.List;

import org.esupportail.formation.domain.beans.User;
import org.esupportail.formation.domain.beans.Task;

/**
 * @author Yves Deschamps (Universite de Lille 1) - 2010
 * 
 */
public interface DomainService extends Serializable {
	
	/**
	 * @param uid
	 * @return a user.
	 */
	public User getUser(String uid);
	public List<Task> getTasks();
	public List<Task> getPublicTasks();
	void addTask(Task task);
	public void deleteTask(Task task);
	public void updateTask(Task task);
	public List<Task> getTasksForUser(User user);
	public void addUser(User user);
	public void deleteUser(User user);
	public List<User> getUsers();
}
