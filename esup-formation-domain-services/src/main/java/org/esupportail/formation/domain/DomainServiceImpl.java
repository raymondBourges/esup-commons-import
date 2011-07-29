/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.domain;

import java.util.ArrayList;
import java.util.List;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.formation.dao.DaoService;
import org.esupportail.formation.domain.beans.Task;
import org.esupportail.formation.domain.beans.User;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yves Deschamps (Universite de Lille 1) - 2010
 * 
 */
public class DomainServiceImpl implements DomainService, InitializingBean {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = 5562208937407153456L;

	/**
	 * For Logging.
	 */
	@SuppressWarnings("unused")
	private final Logger logger = new LoggerImpl(this.getClass());
	private DaoService daoService;

	/**
	 * En l'absence de Dao et de Ldap, on constitue ici une liste... limitee de
	 * fait a l'utilisateur courant.
	 */
	private List<User> users;

	/**
	 * Constructor.
	 */
	public DomainServiceImpl() {
		super();
		users = new ArrayList<User>();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.daoService, 
				"property daoService of class " + this.getClass().getName() + " can not be null");
	}

	@Override
	public User getUser(String uid) {
		User user = null;
		for (User userInList : users) {
			if (userInList.getId().equals(uid)) {
				user = userInList;
				break;
			}
		}
		if (user == null) {
			user = new User();
			user.setId(uid);
			// On cree l'utilisateur, son nom complet prend la valeur de
			// l'Uid.
			user.setDisplayName(uid);
			user.setLanguage("fr");
			users.add(user);
		}
		return user;
	}

	public List<Task> getTasks() {
		return daoService.getTasks();
	}
	
	/**
	 * @see org.esupportail.todolist.domain.DomainService#addUser(org.esupportail.todolist.domain.beans.User)
	 */
	@Override
	public void addTask(Task task) {
		Task tmp = daoService.getTask(task.getId());
		if (tmp == null) { 
			// task does not already exists in database
			logger.debug("addTask -> not found "+task.getId());
			daoService.addTask(task);			
		}
		else {
			daoService.updateTask(task);
			logger.debug("addTask -> found "+task.getId());
		}
		
	}
	///////////////////////////
	// DaoService
	///////////////////////////

	public DaoService getDaoService() {
		return daoService;
	}

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
}
