/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.domain;

import java.util.List;

import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.commons.services.ldap.LdapUser;
import org.esupportail.commons.services.ldap.LdapUserService;
import org.esupportail.commons.services.ldap.SearchableLdapUserServiceImpl;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.todolist.dao.DaoService;
import org.esupportail.todolist.domain.beans.Task;
import org.esupportail.todolist.domain.beans.User;
import org.springframework.beans.factory.InitializingBean;

/**
 * The basic implementation of DomainService.
 * 
 * See /properties/domain/domain-example.xml
 */
public class DomainServiceImpl implements DomainService, InitializingBean {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -8200845058340254019L;

	/**
	 * {@link DaoService}.
	 */
	private DaoService daoService;
 
	/**
	 * {@link LdapUserService}.
	 */
	private LdapUserService ldapUserService;

	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(getClass());

	/**
	 * Bean constructor.
	 */
	public DomainServiceImpl() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("in afterPropertiesSet");
		Assert.notNull(this.daoService, 
				"property daoService of class " + this.getClass().getName() + " can not be null");
	}

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////


	/**
	 * @see org.esupportail.todolist.domain.DomainService#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		return this.daoService.getUsers();
	}


	/**
	 * @see org.esupportail.todolist.domain.DomainService#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String id) throws UserNotFoundException {
		
		return this.daoService.getUser(id);	
	}
	public User getUserFromLdap(String id) throws UserNotFoundException {
		LdapUser ldapUser=this.ldapUserService.getLdapUser(id);
		if (ldapUser!=null){
			User u=new User();
			u.setId(ldapUser.getId());
			u.setDisplayName(ldapUser.getAttribute("displayName"));
			u.setEmail(ldapUser.getAttribute("mail"));
			return u;	
		}
		else
			throw new UserNotFoundException("L'utilisateur "+id+" n'existe pas dans l'annuaire");
	}

	/**
	 * @see org.esupportail.todolist.domain.DomainService#deleteUser(org.esupportail.todolist.domain.beans.User)
	 */
	@Override
	public void deleteUser(User user) {
		daoService.deleteUser(user);
	}

	/**
	 * @see org.esupportail.todolist.domain.DomainService#addUser(org.esupportail.todolist.domain.beans.User)
	 */
	@Override
	public void addUser(User user) {
		User tmp = daoService.getUser(user.getId());
		if (tmp == null) { 
			// user does not already exists in database
			daoService.addUser(user);			
		}
		else {
			user.setInformations(tmp.getInformations());
			daoService.updateUser(user);
		}
		
	}
    //////////////////////////////////////////////////////////////
	// Task
	//////////////////////////////////////////////////////////////


	/**
	 * @see org.esupportail.todolist.domain.DomainService#getTasks(org.esupportail.todolist.domain.beans.Task)
	 */
	@Override
	public List<Task> getTasks() {
		return daoService.getTasks();
	}
	public List<Task> getPublicTasks() {
		return daoService.getPublicTasks();
	}
	public List<Task> getUserTasks(User u) {
		return daoService.getTasksForUser(u);
	}
	public void deleteTask(Task task) {
		daoService.deleteTask(task);
	}

	/**
	 * @see org.esupportail.todolist.domain.DomainService#addUser(org.esupportail.todolist.domain.beans.User)
	 */
	@Override
	public void addTask(Task task) {
		Task tmp = daoService.getTask(task.getId());
		logger.debug("addTask -> get task "+task.getId());
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
	//////////////////////////////////////////////////////////////
	// Misc
	//////////////////////////////////////////////////////////////


	/**
	 * @param daoService the daoService to set
	 */
	public void setDaoService(final DaoService daoService) {
		this.daoService = daoService;
	}

	public LdapUserService getLdapUserService() {
		return ldapUserService;
	}

	public void setLdapUserService(LdapUserService ldapUserService) {
		this.ldapUserService = ldapUserService;
	}

}
