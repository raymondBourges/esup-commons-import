/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.blank.web.controllers;

import org.esupportail.blank.domain.beans.User;
import org.esupportail.blank.services.auth.Authenticator;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.utils.strings.StringUtils;
import org.esupportail.commons.web.controllers.ExceptionController;

/**
 * A bean to memorize the context of the application.
 */
public class SessionController extends AbstractDomainAwareBean {

	/*
	 ******************* PROPERTIES ******************** */
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -5936434246704000653L;

	/**
	 * The exception controller (called when logging in/out).
	 */
	private ExceptionController exceptionController;
	
	/**
	 * The authenticator.
	 */
	private Authenticator authenticator;
	
	/**
	 * The CAS logout URL.
	 */
	private String casLogoutUrl;
	
	
	/*
	 ******************* INIT ******************** */
	
	/**
	 * Constructor.
	 */
	public SessionController() {
		super();
	}

	/**
	 * @see org.esupportail.blank.web.controllers.AbstractDomainAwareBean#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		Assert.notNull(this.exceptionController, "property exceptionController of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.authenticator, "property authenticator of class " 
				+ this.getClass().getName() + " can not be null");
	}

	
	/*
	 ******************* CALLBACK ******************** */
	
	
	/*
	 ******************* METHODS ******************** */
	
	/**
	 * @return the current user, or null if guest.
	 * @throws Exception 
	 */
	@Override
	public User getCurrentUser() throws Exception {
		return authenticator.getUser();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName() + "#" + hashCode();
	}

	

	/*
	 ******************* ACCESSORS ******************** */
	
	
	
	
	/**
	 * @param exceptionController the exceptionController to set
	 */
	public void setExceptionController(final ExceptionController exceptionController) {
		this.exceptionController = exceptionController;
	}

	/**
	 * @param authenticator the authenticator to set
	 */
	public void setAuthenticator(final Authenticator authenticator) {
		this.authenticator = authenticator;
	}
	
	/**
	 * @return the casLogoutUrl
	 */
	protected String getCasLogoutUrl() {
		return casLogoutUrl;
	}

	/**
	 * @param casLogoutUrl the casLogoutUrl to set
	 */
	public void setCasLogoutUrl(final String casLogoutUrl) {
		this.casLogoutUrl = StringUtils.nullIfEmpty(casLogoutUrl);
	}
	
}
