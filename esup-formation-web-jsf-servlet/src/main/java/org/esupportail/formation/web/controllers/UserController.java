package org.esupportail.formation.web.controllers;

import org.esupportail.commons.services.authentication.AuthenticationService;
import org.esupportail.formation.services.auth.Authenticator;
import org.springframework.util.Assert;

public class UserController extends AbstractContextAwareController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1084603907906407867L;
	//private AuthenticationService authenticationService;
	private Authenticator authenticator;
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
	//	Assert.notNull(authenticationService, "property authenticationService of class "
		//		+ this.getClass().getName() + " can not be null");
		Assert.notNull(authenticator, "property authenticator of class "
				+ this.getClass().getName() + " can not be null");
	}
	
	public String goToUserManagerPage(){
		return "go_userManagerPage";
	}
	public String getCurrentUserLogin(){

		try {
			if (authenticator.getUser()!=null)
				return authenticator.getUser().getLogin();
			else
				return "Invité";
		} catch (Exception e) {
			return "Invité";
		}
	}
/*
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
*/
	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

}


