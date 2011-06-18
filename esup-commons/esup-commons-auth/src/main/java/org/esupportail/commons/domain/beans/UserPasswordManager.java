/**
 * ESUP-Portail Commons - Copyright (c) 2006-2009 ESUP-Portail consortium.
 */
package org.esupportail.commons.domain.beans;

import java.io.Serializable;

import org.esupportail.commons.exceptions.PasswordException;

/**
 * The interface of password managers.
 */
public interface UserPasswordManager extends Serializable {

	/**
	 * @return a ramdom password.
	 */
	String generate();

	/**
	 * Check a password.
	 * @param password the password to check
	 * @throws PasswordException 
	 */
	void check(final String password) throws PasswordException;

}
