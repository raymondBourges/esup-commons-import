package org.esupportail.formation.web.controllers;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.esupportail.formation.domain.beans.User;
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
	public String getCurrentUserName(){

		try {
			if (authenticator.getUser()!=null)
				if(authenticator.getUser().getDisplayName()!=null)
					return authenticator.getUser().getDisplayName();
				else
					return getString("USER.UNKNOWN",authenticator.getUser().getLogin());
			else
				return getString("USER.GUEST");
		} catch (Exception e) {
			return getString("USER.GUEST");
		}
	}
	
	public boolean isUserLogged(){

		try {
			if (authenticator.getUser()!=null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	public String goLogout(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			getSessionController().logout();
			facesContext.getExternalContext().redirect("https://cas.uhp-nancy.fr/cas/logout?service=http://localhost:8080");
			facesContext.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "go_home";
		//return null;
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

}


