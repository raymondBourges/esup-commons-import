/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.domain;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebService;

import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.todolist.domain.beans.Task;
import org.esupportail.todolist.domain.beans.User;

/**
 * The domain service interface.
 */
@WebService
public interface DomainService extends Serializable {

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////

	/**
	 * @param id
	 * @return the User instance that corresponds to an id.
	 * @throws UserNotFoundException
	 */
	User getUser(String id) throws UserNotFoundException;
	User getUserFromLdap(String id) throws UserNotFoundException;
	/**
	 * @return the list of all the users.
	 */
	List<User> getUsers();

	/**
	 * Delete an user.
	 * @param user
	 */
	void deleteUser(User user);

	/**
	 * Add an user.
	 * @param user
	 */
	void addUser(User user);

	List<Task> getTasks();
	List<Task> getPublicTasks();
	List<Task> getUserTasks(User u);
	void addTask(Task task);
	void deleteTask(Task task);
}
