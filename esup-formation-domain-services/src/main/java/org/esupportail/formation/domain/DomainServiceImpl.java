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
	 * Constructor.
	 */
	public DomainServiceImpl() {
		super();
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.daoService, 
				"property daoService of class " + this.getClass().getName() + " can not be null");
	}
	
	///////////////////////////
	// User
	///////////////////////////

	@Override
	public User getUser(String uid) {
		User user = null;
		for (User userInList : getDaoService().getUsers()) {
			if (userInList.getLogin().equals(uid)) {
				user = userInList;
				break;
			}
		}
		if (user == null) {
			user = new User();
			user.setLogin(uid);
			// On cree l'utilisateur, son nom complet prend la valeur de
			// l'Uid.
			user.setDisplayName(uid);
			user.setLanguage("fr");
		}
		return user;
	}
	
	public void addUser(User user){
		User tmp = daoService.getUser(user.getLogin());
		if (tmp == null) { 
			daoService.addUser(user);			
		}
	}
	public void deleteUser(User user){
		daoService.deleteUser(user);
	}
	public List<User> getUsers() {
		return daoService.getUsers();
	}
	
	///////////////////////////
	// Task
	///////////////////////////
	public List<Task> getTasks() {
		return daoService.getTasks();
	}
	
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
	public void deleteTask(Task task) {
		daoService.deleteTask(task);
	}
	public void updateTask(Task task) {
		daoService.updateTask(task);
	}
	@Override
	public List<Task> getTasksForUser(User user) {
		return daoService.getTasksForUser(user);
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
