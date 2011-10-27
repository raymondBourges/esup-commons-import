package org.esupportail.formation.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.esupportail.commons.services.ldap.LdapUser;
import org.esupportail.commons.services.ldap.LdapUserService;
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
	
	private String searchUser;
	private User chosenUser;
	private List<User> resultSearch;
	private LdapUserService ldapUserService;
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

	public void searchUserInLdap(){
		List<LdapUser> listOfLdapUser = ldapUserService.getLdapUsersFromToken(searchUser);
		resultSearch=new ArrayList<User>();
		for (LdapUser ldapUser : listOfLdapUser) {
			User user=new User();
			user.setLogin(ldapUser.getAttribute("uid"));
			user.setDisplayName(ldapUser.getAttribute("displayName"));
			resultSearch.add(user);
		}
	}
	public void addUserFromLdap(){
		getDomainService().addUser(chosenUser);
		
	}
	
	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	public String getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}

	public User getChosenUser() {
		return chosenUser;
	}

	public void setChosenUser(User chosenUser) {
		this.chosenUser = chosenUser;
	}

	public List<User> getResultSearch() {
		return resultSearch;
	}

	public void setResultSearch(List<User> resultSearch) {
		this.resultSearch = resultSearch;
	}

	public LdapUserService getLdapUserService() {
		return ldapUserService;
	}

	public void setLdapUserService(LdapUserService ldapUserService) {
		this.ldapUserService = ldapUserService;
	}

}


