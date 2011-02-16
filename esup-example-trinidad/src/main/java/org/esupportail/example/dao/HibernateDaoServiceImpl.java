/**
 * ESUP-Portail example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.dao;

import java.util.List;

import org.esupportail.commons.dao.AbstractJdbcJndiHibernateDaoService;
import org.esupportail.example.domain.beans.User;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public class HibernateDaoServiceImpl extends
		AbstractJdbcJndiHibernateDaoService implements DaoService {

	/**
	 * Bean constructor.
	 */
	public HibernateDaoServiceImpl() {
		super();
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getUser(java.lang.String)
	 */
	public User getUser(final String id) {		
		// En cas d'arrivée via une authentification sso-cas
		getHibernateTemplate().setAllowCreate(true);		
		return getHibernateTemplate().get(User.class, id);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getUsers()
	 */
	@SuppressWarnings( { "unchecked", "cast" })
	public List<User> getUsers() {
		String request = "from User Order by id";
		return (List<User>) getHibernateTemplate().find(request);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#addUser(org.esupportail.example.domain.beans.User)
	 */
	public synchronized void addUser(User user) {
		addObject(user);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#deleteUser(org.esupportail.example.domain.beans.User)
	 */
	public void deleteUser(User user) {
		deleteObject(user);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#updateUser(org.esupportail.example.domain.beans.User)
	 */
	public void updateUser(User user) {
		updateObject(user);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getAdministrators()
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAdministrators() {
		// En cas d'arrivée via une servlet Json
		getHibernateTemplate().setAllowCreate(true);		
		String request = "from User where admin = true";
		return getHibernateTemplate().find(request);
	}

}