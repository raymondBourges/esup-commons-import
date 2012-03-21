/**
 * ESUP-Portail Commons - Copyright (c) 2006-2009 ESUP-Portail consortium.
 */
package org.esupportail.commons.services.authentication;

/** 
 * A Shibbolized portal authenticator.
 */
public class LiferayPortalAuthenticationService extends PortalAuthenticationService {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 1183603391443511495L;

	/**
	 * Bean constructor.
	 */
	public LiferayPortalAuthenticationService() {
		super();
	}

	/**
	 * @see org.esupportail.commons.services.authentication.AbstractTypedAuthenticationService#getAuthType()
	 */
	@Override
	protected String getAuthType() {
		return AuthUtils.LIFERAY;
	}

	/**
	 * @see org.esupportail.commons.services.authentication.AbstractTypedAuthenticationService#setAuthType(
	 * java.lang.String)
	 */
	@Override
	public void setAuthType(final String authType) {
		throw new UnsupportedOperationException(
				"method " + getClass() + ".setAuthType() should never be called.");
	}

}
