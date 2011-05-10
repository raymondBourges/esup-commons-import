/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.web.controllers;

import java.util.List;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.todolist.domain.beans.User;
import org.esupportail.todolist.services.application.TodoApplicationService;
import org.esupportail.todolist.web.beans.UserBean;
import org.esupportail.todolist.web.beans.UserPaginator;


/**
 * A visual bean for the welcome page.
 */
public class UserController  extends AbstractContextAwareController {

	/*
	 ******************* PROPERTIES ******************** */

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -239570715531002003L;

	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(this.getClass());

	

	/**
	 * The user.
	 */
	private UserBean userToUpdate;

	/**
	 * The user paginator used to display users informations
	 */
	private UserPaginator userPaginator;
	

	

	/*
	 ******************* INIT ******************** */

	/**
	 * Bean constructor.
	 */
	public UserController() {
		super();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode();
	}

	/**
	 * @see org.esupportail.todolist.web.controllers.AbstractDomainAwareBean#reset()
	 */
	@Override
	public void reset() {
		super.reset();		
	}

	/**
	 * @see org.esupportail.todolist.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		Assert.notNull(this.userPaginator, "property userPaginator of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.userToUpdate, "property userToUpdate of class " 
				+ this.getClass().getName() + " can not be null");
		userPaginator.forceReload();
	}

	

	/**
	 * Delete the user.
	 */
	public void deleteUser() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering deleteUser with userToUpdate = " + userToUpdate);
		}
		User u = new User();
		u.setId(userToUpdate.getId());
		u.setDisplayName(userToUpdate.getDisplayName());
		getDomainService().deleteUser(u);
		userPaginator.forceReload();
		addInfoMessage(null, "INFO.DELETE.SUCCESS");
	}

	/**
	 * @param userToDelete the userToDelete to set
	 */
	public void setUserToDelete(final User userToDelete) {
		userToUpdate = new UserBean();
		userToUpdate.setId(userToDelete.getId());
		userToUpdate.setDisplayName(userToDelete.getDisplayName());
	}
	
	/**
	 * Add the user.
	 */
	public void addUser() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering addUser with userToUpdate = " + userToUpdate);
		}
		User u = new User();
		if (((TodoApplicationService)getApplicationService()).isUseLdap()){// Si on utilise LDAP on cherche le user et on l'ajoute en base
			u=getDomainService().getUserFromLdap(userToUpdate.getId());
		}
		else{
			u.setId(userToUpdate.getId());
			u.setDisplayName(userToUpdate.getDisplayName());
			u.setEmail(userToUpdate.getEmail());
			u.setLanguage("fr");
			u.setAdmin(false);
		}
		getDomainService().addUser(u);
		userToUpdate.reset();
		userPaginator.forceReload();
		addInfoMessage(null, "INFO.ENTER.SUCCESS");
	}


	/**
	 * @return
	 */
	public String getCurrentUserId() {
        if (getCurrentUser()!=null)
            return getCurrentUser().getId();
        else
            return null;
    }

	
	/*
	 ******************* ACCESSORS ******************** */

	/**
	 * @return User
	 */
	
	public List<User> getUsers() {
		return userPaginator.getVisibleItems();
	}


	/**
	 * @return the userPaginator
	 */
	public UserPaginator getUserPaginator() {
		return userPaginator;
	}

	/**
	 * @param userPaginator the userPaginator to set
	 */
	public void setUserPaginator(final UserPaginator userPaginator) {
		this.userPaginator = userPaginator;
	}

	/**
	 * @return the userToUpdate
	 */
	public UserBean getUserToUpdate() {
		return userToUpdate;
	}

	/**
	 * @param userToUpdate the userToUpdate to set
	 */
	public void setUserToUpdate(final UserBean userToUpdate) {
		this.userToUpdate = userToUpdate;
	}


}
