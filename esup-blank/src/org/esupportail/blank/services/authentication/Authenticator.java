package org.esupportail.blank.services.authentication;

import org.esupportail.blank.domain.beans.User;

/**
 * The interface of authenticators.
 */
public interface Authenticator {

	/**
	 * @return the authenticated user.
	 */
	User getUser();

}