package org.esupportail.todolist.services.authentication;

import org.esupportail.todolist.domain.beans.User;


/**
 * The interface of authenticators.
 */
public interface Authenticator {

	/**
	 * @return the authenticated user.
	 */
	User getUser();

}