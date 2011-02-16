/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.dao;

import java.net.URL;
import java.util.List;

import org.esupportail.example.domain.beans.User;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public interface DaoService {

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
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * Delete a user.
	 * 
	 * @param user
	 */
	void deleteUser(User user);

	/**
	 * Update a user.
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * @return administrators of the application.
	 */
	List<User> getAdministrators();

}
