/**
 * ESUP-Portail Example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.commons.services.database.DatabaseUtils;
import org.esupportail.commons.services.ldap.LdapUser;
import org.esupportail.commons.services.ldap.LdapUserService;
import org.esupportail.example.dao.DaoService;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.services.i18n.BundleService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public class DomainServiceImpl implements DomainService, InitializingBean {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = 5562208937407153456L;

	private final Logger logger = Logger.getLogger(this.getClass());

	private List<User> administrators;

	private String firstAdmin;

	private DaoService daoService;

	private LdapUserService ldapUserService;

	private String staffFilter;

	/**
	 * Constructor.
	 */
	public DomainServiceImpl() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		String[] args = {"", this.getClass().getName()};
		if (this.firstAdmin == null) {
			args[0] = "firstAdmin";
		}
		if (this.daoService == null) {
			args[0] = "daoService";
		}
		if (this.ldapUserService == null) {
			args[0] = "ldapUserService";
		}
		if (this.staffFilter == null) {
			args[0] = "staffFilter";
		}
		if (!args[0].equals("")) {
			throw new Exception(BundleService
					.getString("CONFIG_EXCEPTION.TITLE", args));
		}
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#getAdministrators()
	 */
	public List<User> getAdministrators() {
		administrators = daoService.getAdministrators();
		return administrators;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#getUser(java.lang.String)
	 */
	public User getUser(String uid) {
		User user = daoService.getUser(uid);
		if (user == null) {
			user = new User();
			user.setId(uid);
			// On crée l'utilisateur en recherchant son nom complet dans
			// l'annuaire Ldap.
			try {
				LdapUser ldapUser = ldapUserService.getLdapUser(uid);
				user.setDisplayName(ldapUser.getAttribute("displayName"));
			} catch (UserNotFoundException e) {
				user.setDisplayName(BundleService
						.getString("USER.ERROR.DISPLAY_NAME"));
			}
			user.setLanguage("fr");
			user.setAccessibilityMode("default");
			if (administrators == null || administrators.size() == 0) {
				if (uid.equals(firstAdmin)) {
					user.setAdmin(true);
				}
			}
			DatabaseUtils.begin();
			DatabaseUtils.open();
			daoService.addUser(user);
			DatabaseUtils.commit();
			DatabaseUtils.close();
		} else {
			if (getAdministrators().contains(user) && !(user.isAdmin())) {
				user.setAdmin(true);
				daoService.updateUser(user);
			}
		}
		return user;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#deleteAdministrator(org.esupportail.example.domain.beans.User)
	 */
	public void deleteAdministrator(User administratorToDelete) {
		administrators.remove(administratorToDelete);
		administratorToDelete.setAdmin(false);
		daoService.updateUser(administratorToDelete);
	}

	/**
	 * @param firstAdmin
	 *            the firstAdmin to set
	 */
	public void setFirstAdmin(String firstAdmin) {
		this.firstAdmin = firstAdmin;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#searchPerson(java.lang.String)
	 */
	public List<User> searchPerson(String searchPerson) {
		List<User> users = new ArrayList<User>();
		String beginFilter = "(&(";
		String searchFilter = "(displayName=*" + searchPerson + "*)";
		String endFilter = "))";
		String filter = beginFilter + searchFilter + staffFilter + endFilter;
		if (logger.isDebugEnabled()) {
			logger.debug(filter);
		}
		List<LdapUser> ldapUsers = ldapUserService
				.getLdapUsersFromFilter(filter);
		for (LdapUser ldapUser : ldapUsers) {
			String uid = ldapUser.getId();
			User user = daoService.getUser(uid);
			if (user == null) {
				// On en profite pour créer la personne
				user = new User();
				user.setId(uid);
				user.setAdmin(false);
				user.setDisplayName(ldapUser.getAttribute("displayName"));
				user.setLanguage("fr");
				user.setAccessibilityMode("default");
				daoService.addUser(user);
			}
			users.add(user);
		}
		return users;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#setAdministrators(java.util.List)
	 */
	public void setAdministrators(List<User> administrators) {
		this.administrators = administrators;
	}

	/**
	 * @param daoService
	 *            the daoService to set
	 */
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#addAdministrator(java.lang.String)
	 */
	public void addAdministrator(String uid) {
		User user = daoService.getUser(uid);
		user.setAdmin(true);
		daoService.updateUser(user);
	}

	/**
	 * @param ldapUserService
	 *            the ldapUserService to set
	 */
	public void setLdapUserService(LdapUserService ldapUserService) {
		this.ldapUserService = ldapUserService;
	}

	/**
	 * @param staffFilter
	 *            the staffFilter to set
	 */
	public void setStaffFilter(String staffFilter) {
		this.staffFilter = staffFilter;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#updateUser(org.esupportail.example.domain.beans.User)
	 */
	public void updateUser(User user) {
		daoService.updateUser(user);
	}

}
