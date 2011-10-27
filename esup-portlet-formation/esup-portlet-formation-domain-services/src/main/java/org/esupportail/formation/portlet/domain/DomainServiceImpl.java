/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.portlet.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.esupportail.formation.domain.DomainServiceImplService;
import org.esupportail.formation.domain.Task;
import org.esupportail.formation.portlet.domain.beans.User;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
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
		// nothing to do yet.
	}

	@Override
	public User getUser(String uid) {
		User user = null;
		for (User userInList : users) {
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
			users.add(user);
		}
		return user;
	}

	public List<Task> getTasks(String login,String wsdl,int limit){
		org.esupportail.formation.domain.DomainService ds = getWs(wsdl);
		org.esupportail.formation.domain.User u=new org.esupportail.formation.domain.User();
		u.setLogin(login);
		return ds.   getLastTasksForUser(u,limit);	
	}
	public List<Task> getAllTasks(String wsdl){
		org.esupportail.formation.domain.DomainService ds = getWs(wsdl);
		return ds.getTasks();	
	}
	private org.esupportail.formation.domain.DomainService getWs(String wsdl){
		org.esupportail.formation.domain.DomainService ds = null;
		try {
			ds = new DomainServiceImplService(new URL(wsdl)).getDomainServiceImplPort();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
