/**
 * ESUP-Portail Commons - Copyright (c) 2006-2009 ESUP-Portail consortium.
 */
package org.esupportail.commons.domain.beans;

import org.esupportail.commons.exceptions.PasswordException;
import org.springframework.beans.factory.InitializingBean;

/**
 * a basic password manager implementation.
 */
public class UserPasswordManagerImpl implements UserPasswordManager, InitializingBean {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -2486990059811696496L;

	/**
	 * The default minimum length for passwords.
	 */
	private static final int DEFAULT_PASSWORD_MIN_LENGTH = 6;

	/**
	 * The default maximum length for passwords.
	 */
	private static final int DEFAULT_PASSWORD_MAX_LENGTH = 8;
	
	/**
	 * The minimum length for passwords.
	 */
	private Integer passwordMinLength;

	/**
	 * The maximum length for passwords.
	 */
	private Integer passwordMaxLength;

	/**
	 * Bean constructor.
	 */
	public UserPasswordManagerImpl() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() {
		if (passwordMaxLength == null || passwordMaxLength < 1) {
			passwordMaxLength = DEFAULT_PASSWORD_MAX_LENGTH;
		}
		if (passwordMinLength == null || passwordMinLength < 1) {
			passwordMinLength = DEFAULT_PASSWORD_MIN_LENGTH;
		}
	}

	/**
	 * @see org.esupportail.commons.domain.UserPasswordManager#generate()
	 */
	public String generate() {
		StringBuffer sb = new StringBuffer(8);
		Math.random();
		for (int i = 0; i < 8; i++) {
			char intChar = 0;
			while (intChar < 48 
					|| intChar > 122 
					|| (intChar > 57 && intChar < 65)
					|| (intChar > 90 && intChar < 97)) {
				Math.random();
				intChar = (char) (Math.random() * 100);
			}
			sb.append(intChar);
		}
		return sb.toString();
	}

	/**
	 * @see org.esupportail.commons.domain.UserPasswordManager#check(java.lang.String, java.util.Locale)
	 */
	public void check(final String password) throws PasswordException {
		if (password == null) {
			throw new PasswordException("PASSWORD_ERROR.NULL");
		}
		if (password.length() > passwordMaxLength) {
			throw new PasswordException("PASSWORD_ERROR.TOO_LONG{" + passwordMaxLength + "}");
		}
		if (password.length() < passwordMinLength) {
			throw new PasswordException("PASSWORD_ERROR.TOO_SHORT" + passwordMinLength + "}");
		}
	}

	/**
	 * @param passwordMaxLength the passwordMaxLength to set
	 */
	public void setPasswordMaxLength(final Integer passwordMaxLength) {
		this.passwordMaxLength = passwordMaxLength;
	}

	/**
	 * @param passwordMinLength the passwordMinLength to set
	 */
	public void setPasswordMinLength(final Integer passwordMinLength) {
		this.passwordMinLength = passwordMinLength;
	}

}
