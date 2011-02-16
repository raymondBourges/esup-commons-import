/**
 * ESUP-Portail Example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.services.auth;

import org.esupportail.example.domain.beans.User;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public interface Authenticator {

	/**
	 * @return the authenticated user.
	 * @throws Exception 
	 */
	User getUser() throws Exception;

}