/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.esupportail.commons.exceptions.ConfigException;
import org.esupportail.commons.services.ldap.LdapUserService;
import org.esupportail.commons.utils.Assert;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.web.beans.PrintableThingSet;

/**
 * A visual bean for the welcome page.
 */
public class WelcomeController extends AbstractContextAwareController {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 4659933721493757039L;

	/**
	 * ldap Service.
	 */
	private LdapUserService ldapUserService;
	
	/**
	 * The printable Thing sets.
	 */
	private List<PrintableThingSet> printableThingSets;

	/**
	 * Bean constructor.
	 */
	public WelcomeController() {
		super();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		Assert.notNull(ldapUserService, 
				"property ldapUserService of class " + this.getClass().getName() + " can not be null");
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractDomainAwareBean#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		enter();
	}

	/**
	 * @return true if the current user is allowed to view the page.
	 */
	public boolean isPageAuthorized() {
		return true;
	}

	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String enter() {
		if (!isPageAuthorized()) {
			addUnauthorizedActionMessage();
			return null;
		}
		printableThingSets = new ArrayList<PrintableThingSet>();
		User currentUser = getCurrentUser();
		if (currentUser != null) {
			for (Department department : getDomainService().getVisibleDepartments(currentUser)) {
				printableThingSets.add(new PrintableThingSet(
						department, getDomainService().getThings(department)));
			}
		}
		return "navigationWelcome";
	}

	/**
	 * @return nothing
	 */
	public String throwException() {
		throw new ConfigException("test"); 
	}

	/**
	 * @param ldapUserService the ldapUserService to set
	 */
	public void setLdapService(final LdapUserService ldapUserService) {
		this.ldapUserService = ldapUserService;
	}

	/**
	 * @return the printableThingSets
	 */
	public List<PrintableThingSet> getPrintableThingSets() {
		return printableThingSets;
	}
	
}
