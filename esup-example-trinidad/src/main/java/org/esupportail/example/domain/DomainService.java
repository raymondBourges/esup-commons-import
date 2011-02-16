/**
 * ESUP-Portail Example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.domain;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

import org.esupportail.example.domain.beans.User;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public interface DomainService extends Serializable {
	
	/**
	 * @param uid
	 * @return a user.
	 */
	User getUser(String uid);

	/**
	 * @return the application administrators.
	 */
	List<User> getAdministrators();

	/**
	 * Delete an administrator.
	 * @param administratorToDelete
	 */
	void deleteAdministrator(User administratorToDelete);

	/**
	 * Search a person from a String (a part of the name by example).
	 * @param searchPerson
	 * @return a lsit of persons (User).
	 */
	List<User> searchPerson(String searchPerson);

	/**
	 * @param administrators
	 */
	void setAdministrators(List<User> administrators);

	/**
	 * @param uid
	 */
	void addAdministrator(String uid);

	/**
	 * @param user
	 */
	void updateUser(User user);
	
}
