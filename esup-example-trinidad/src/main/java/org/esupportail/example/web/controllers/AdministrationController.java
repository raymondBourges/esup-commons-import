/**
 * ESUP-Portail example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.esupportail.commons.utils.BeanUtils;
import org.esupportail.example.domain.DomainService;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.services.i18n.BundleService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public class AdministrationController implements Serializable, InitializingBean {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = -4133220917313990369L;

	private DomainService domainService;

	private User administratorToDelete;

	private boolean newAdministratorFormInPlace;

	private String searchPerson;

	private List<User> resultSearchPerson;

	private List<SelectItem> personItems;

	private List<User> administrators;

	private String uidToAdd;

	private String administrationUrl;

	/**
	 * Constructor.
	 */
	public AdministrationController() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		String[] args = {"", this.getClass().getName()};
		if (this.domainService == null) {
			args[0] = "domainService";
		}
		if (this.administrationUrl == null) {
			args[0] = "administrationUrl";
		}
		if (!args[0].equals("")) {
			throw new Exception(BundleService
					.getString("CONFIG_EXCEPTION.TITLE", args));
		}
	}

	/**
	 * @param domainService
	 *            the domainService to set
	 */
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * @param administrationUrl
	 *            the administrationUrl to set
	 */
	public void setAdministrationUrl(String administrationUrl) {
		this.administrationUrl = administrationUrl;
	}

	/**
	 * @return the administrators
	 */
	public List<User> getAdministrators() {
		administrators = domainService.getAdministrators();
		Collections.sort(administrators, User.ORDER_DISPLAYNAME);
		return administrators;
	}

	/**
	 * @param administratorToDelete
	 *            the administratorToDelete to set
	 */
	public void setAdministratorToDelete(User administratorToDelete) {
		this.administratorToDelete = administratorToDelete;
	}

	/**
	 * Delete an administrator.
	 * 
	 * @return null
	 */
	public String deleteAdministratorAction() {
		administrators.remove(administratorToDelete);
		domainService.deleteAdministrator(administratorToDelete);
		return "administration";
	}

	/**
	 * @return the administrators list size.
	 */
	public int getAdministratorsSize() {
		return getAdministrators().size();
	}

	/**
	 * Make form in place to find a new administrator.
	 * 
	 * @return null
	 */
	public String newAdministratorAction() {
		newAdministratorFormInPlace = true;
		SessionController sessionController = (SessionController) BeanUtils.getBean("sessionController");
		sessionController.setAction("administration");
		return "administration";
	}

	/**
	 * @return the newAdministratorFormInPlace
	 */
	public boolean isNewAdministratorFormInPlace() {
		return newAdministratorFormInPlace;
	}

	/**
	 * @return the searchPerson
	 */
	public String getSearchPerson() {
		return searchPerson;
	}

	/**
	 * @param searchPerson
	 *            the searchPerson to set
	 */
	public void setSearchPerson(String searchPerson) {
		this.searchPerson = searchPerson;
	}

	/**
	 * reset all properties.
	 */
	public void reset() {
		searchPerson = null;
		personItems = null;
		uidToAdd = null;
		newAdministratorFormInPlace = false;
	}

	/**
	 * Cancel form for new admninistrator.
	 * 
	 * @return null
	 */
	public String resetNewAdministratorAction() {
		reset();
		return "administration";
	}

	/**
	 * If an new administrator is selected, add this on in administrators list.
	 * Otherwise, make a parsons list for choice.
	 * 
	 * @return null;
	 */
	public String searchAdministratorAction() {
		if (uidToAdd != null) {
			domainService.addAdministrator(uidToAdd);
			administrators.add(domainService.getUser(uidToAdd));
			domainService.setAdministrators(administrators);
			reset();
			return "administration";
		} else {
			resultSearchPerson = domainService.searchPerson(searchPerson);
			personItems = new ArrayList<SelectItem>();
			if (resultSearchPerson.size() != 0) {
				Collections.sort(resultSearchPerson, User.ORDER_DISPLAYNAME);
				for (User user : resultSearchPerson) {
					if (!administrators.contains(user)) {
						personItems.add(new SelectItem(user.getId(), user
								.getDisplayName()));
					}
				}
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_WARN, "", BundleService
								.getString("ADMINISTRATION.ERROR.SEARCH3")));
			}
			return "administration";
		}
	}

	/**
	 * @return the personItems
	 */
	public List<SelectItem> getPersonItems() {
		return personItems;
	}

	/**
	 * @return the uidToAdd
	 */
	public String getUidToAdd() {
		return uidToAdd;
	}

	/**
	 * @param uidToAdd
	 *            the uidToAdd to set
	 */
	public void setUidToAdd(String uidToAdd) {
		this.uidToAdd = uidToAdd;
	}

	/**
	 * @return the administratorToDelete
	 */
	public User getAdministratorToDelete() {
		return administratorToDelete;
	}

	/**
	 * @return an Url (with the good host, port and context...).
	 */
	public String getPrintUrl() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getExternalContext().getRequestContextPath()
				+ "/stylesheets/print/administration.pdf.jsf";
	}

}
