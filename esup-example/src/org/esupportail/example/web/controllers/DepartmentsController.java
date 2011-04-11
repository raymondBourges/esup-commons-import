/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.commons.services.ldap.LdapUserService;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.web.controllers.LdapSearchCaller;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.DepartmentManager;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.web.beans.DepartmentManagerPaginator;
import org.esupportail.example.web.beans.ManagedDepartmentPaginator;
import org.esupportail.example.web.deepLinking.DeepLinkingRedirectorImpl;
import org.springframework.util.StringUtils;

/**
 * A bean to manage departments.
 */
public class DepartmentsController extends AbstractContextAwareController implements LdapSearchCaller {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 574390869473953058L;

	/**
	 * The LDAP service.
	 */
	private LdapUserService ldapUserService;
	
	/**
	 * The current department.
	 */
	private Department department;

	/**
	 * The department to add.
	 */
	private Department departmentToAdd;
	
	/**
	 * The department to update.
	 */
	private Department departmentToUpdate;
	
	/**
	 * The department manager to add.
	 */
	private DepartmentManager departmentManagerToAdd;
	
	/**
	 * The department manager to update.
	 */
	private DepartmentManager departmentManagerToUpdate;
	
	/**
	 * The department paginator.
	 */
	private ManagedDepartmentPaginator departmentPaginator;
	
	/**
	 * The department manager paginator.
	 */
	private DepartmentManagerPaginator departmentManagerPaginator;
	
	/**
	 * The id of the user to give manager privileges.
	 */
	private String ldapUid;
	
	/**
	 * Bean constructor.
	 */
	public DepartmentsController() {
		super();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		Assert.notNull(this.ldapUserService, 
				"property ldapUserService of class " + this.getClass().getName() + " can not be null");
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		department = null;
		departmentToAdd = new Department();
		departmentToUpdate = null;
		departmentManagerToAdd = new DepartmentManager();
		departmentManagerToUpdate = null;
		ldapUid = null;
		departmentPaginator = new ManagedDepartmentPaginator(getDomainService());
		departmentPaginator.setUser(getCurrentUser());
		departmentManagerPaginator = new DepartmentManagerPaginator(getDomainService());
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() + "[departmentToAdd=" + departmentToAdd
		+ ", departmentToUpdate=" + departmentToUpdate + ", departmentManagerToAdd=" + departmentManagerToAdd
		+ ", departmentManagerToUpdate=" + departmentManagerToUpdate
		+ ", departmentpaginator=" + departmentPaginator
		+ ", departmentManagerPaginator=" + departmentManagerPaginator + "]";
	}

	/**
	 * @return true if the current user is allowed to acces the view.
	 */
	public boolean isPageAuthorized() {
		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return false;
		}
		if (isCurrentUserCanAddDepartment()) {
			return true;
		}
		return !getDomainService().getManagedDepartments(currentUser).isEmpty();
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
		departmentPaginator.setUser(getCurrentUser());
		return "navigationDepartments";
	}
	
	/**
	 * @return true if the current user can add a department
	 */
	public boolean isCurrentUserCanAddDepartment() {
		return getDomainService().userCanAddDepartment(getCurrentUser());
	}

	/**
	 * @return true if the current user is allowed to view the current department.
	 */
	public boolean isCurrentUserCanViewDepartment() {
		return getDomainService().userCanViewDepartment(getCurrentUser(), department);
	}
	
	/**
	 * @return true if the current user is allowed to delete the current department.
	 */
	public boolean isCurrentUserCanDeleteDepartment() {
		return getDomainService().userCanDeleteDepartment(getCurrentUser(), department);
	}
	
	/**
	 * @return true if the current user can add a department manager
	 */
	public boolean isCurrentUserCanAddDepartmentManager() {
		return getDomainService().userCanAddDepartmentManager(getCurrentUser(), department);
	}

	/**
	 * @return true if the current user can edit the department properties
	 */
	public boolean isCurrentUserCanEditDepartmentProperties() {
		return getDomainService().userCanEditDepartmentProperties(getCurrentUser(), department);
	}

	/**
	 * JSF validator.
	 * @param context 
	 * @param component 
	 * @param object 
	 */
	public void validateFilter(
			@SuppressWarnings("unused")
			final FacesContext context, 
			@SuppressWarnings("unused")
			final UIComponent component, 
			final Object object) {
		String ldapFilter = (String) object;
		if (!StringUtils.hasText(ldapFilter)) {
			return;
		}
		String error = ldapUserService.testLdapFilter(ldapFilter); 
		if (error != null) {
			throw new ValidatorException(getFacesErrorMessage("_.MESSAGE.BAD_LDAP_FILTER", error));
		}
	}

	/**
	 * @return a String.
	 */
	public String addDepartment() {
		if (!isCurrentUserCanAddDepartment()) {
			addUnauthorizedActionMessage();
			return null;
		}
		if (getDomainService().isDepartmentLabelUsed(departmentToAdd.getLabel())) {
			addWarnMessage("label", "DEPARTMENTS.MESSAGE.LABEL_ALREADY_USED", departmentToAdd.getLabel());
			return null;
		}
		getDomainService().addDepartment(departmentToAdd);
		departmentPaginator.forceReload();
		setDepartment(departmentToAdd);
		addInfoMessage(null, "DEPARTMENTS.MESSAGE.DEPARTMENT_ADDED", departmentToAdd.getLabel());
		departmentToAdd = new Department();
		return "departmentAdded";
	}

	/**
	 * JSF callback.
	 * @return a String
	 */
	public String updateDepartment() {
		if (!isCurrentUserCanEditDepartmentProperties()) {
			addUnauthorizedActionMessage();
			return null;
		}
		if (!department.getLabel().equals(departmentToUpdate.getLabel())) {
			// the label has changed, check if it is not used
			if (getDomainService().isDepartmentLabelUsed(departmentToUpdate.getLabel())) {
				addWarnMessage("label", "DEPARTMENTS.MESSAGE.LABEL_ALREADY_USED", 
						departmentToUpdate.getLabel());
				return null;
			}
		}
		getDomainService().updateDepartment(departmentToUpdate);
		departmentPaginator.forceReload();
		addInfoMessage(null, "DEPARTMENTS.MESSAGE.DEPARTMENT_UPDATED", departmentToUpdate.getLabel());
		setDepartment(departmentToUpdate);
		return "departmentUpdated";
	}
	
	/**
	 * @return a String.
	 */
	public String addDepartmentManager() {
		if (!isCurrentUserCanAddDepartmentManager()) {
			addUnauthorizedActionMessage();
			return null;
		}
		User user;
		try {
			user = getDomainService().getUser(ldapUid);
		} catch (UserNotFoundException e) {
			addWarnMessage("ldapUid", "_.MESSAGE.USER_NOT_FOUND", ldapUid);
			return null;
		}
		if (getDomainService().isDepartmentManager(department, user)) {
			addWarnMessage("ldapUid", "DEPARTMENTS.MESSAGE.USER_ALREADY_MANAGER", ldapUid);
			return null;
		}
		departmentManagerToAdd.setDepartment(department);
		departmentManagerToAdd.setUser(user);
		getDomainService().addDepartmentManager(departmentManagerToAdd);
		departmentManagerPaginator.forceReload();
		addInfoMessage(
				null, "DEPARTMENTS.MESSAGE.DEPARTMENT_MANAGER_ADDED", user.getDisplayName(), 
				user.getId(), department.getLabel());
		setDepartmentManagerToUpdate(departmentManagerToAdd);
		departmentManagerToAdd = new DepartmentManager();
		ldapUid = "";
		return "departmentManagerAdded";			
	}
	
	/**
	 * @return true if the current user can delete the department manager.
	 */
	public boolean isCurrentUserCanDeleteDepartmentManager() {
		return getDomainService().userCanDeleteManager(getCurrentUser(), departmentManagerToUpdate);
	}

	/**
	 * @return a String.
	 */
	public String confirmDeleteDepartmentManager() {
		if (!isCurrentUserCanDeleteDepartmentManager()) {
			addUnauthorizedActionMessage();
			return null; 
		}
		getDomainService().deleteDepartmentManager(departmentManagerToUpdate);
		departmentManagerPaginator.forceReload();
		addInfoMessage(null, "DEPARTMENTS.MESSAGE.DEPARTMENT_MANAGER_DELETED", 
				departmentManagerToUpdate.getUser().getDisplayName(),
				departmentManagerToUpdate.getUser().getId(),
				departmentManagerToUpdate.getDepartment().getLabel());
		return "departmentManagerDeleted";
	}

	/**
	 * @return true if the current user can edit the department manager.
	 */
	public boolean isCurrentUserCanEditDepartmentManager() {
		return getDomainService().userCanEditDepartmentManagers(getCurrentUser(), department);
	}

	/**
	 * @return a String.
	 */
	public String updateDepartmentManager() {
		if (!isCurrentUserCanEditDepartmentManager()) {
			addUnauthorizedActionMessage();
			return null;
		}
		getDomainService().updateDepartmentManager(departmentManagerToUpdate);
		departmentManagerPaginator.forceReload();
		addInfoMessage(null, "DEPARTMENTS.MESSAGE.DEPARTMENT_MANAGER_UPDATED", 
				departmentManagerToUpdate.getUser().getDisplayName(),
				departmentManagerToUpdate.getUser().getId(),
				departmentManagerToUpdate.getDepartment().getLabel());
		return "departmentManagerUpdated";
	}

	/**
	 * @return a String
	 */
	public String confirmDeleteDepartment() {
		if (!isCurrentUserCanDeleteDepartment()) {
			addUnauthorizedActionMessage();
			return null;
		}
		Department currentDepartment = department;
		getDomainService().deleteDepartment(currentDepartment);
		departmentPaginator.forceReload();
		addInfoMessage(null, "DEPARTMENTS.MESSAGE.DEPARTMENT_DELETED", currentDepartment.getLabel());
		setDepartment(null);
		return "departmentDeleted";
	}

	/**
	 * @return the params for URLs.
	 */
	private Map<String, String> getViewUrlParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put(DeepLinkingRedirectorImpl.PAGE_PARAM, 
				DeepLinkingRedirectorImpl.DEPARTMENT_VIEW_VALUE);
		params.put(DeepLinkingRedirectorImpl.DEPARTMENT_ID_PARAM, 
				Long.toString(department.getId()));
		return params;
	}

	/**
	 * @return the URL.
	 */
	public String getViewUrl() {
		return getUrlGenerator().guestUrl(getViewUrlParams());
	}

	/**
	 * @return the URL via CAS.
	 */
	public String getViewUrlViaCas() {
		return getUrlGenerator().casUrl(getViewUrlParams());
	}

	/**
	 * @return the departmentToAdd
	 */
	public Department getDepartmentToAdd() {
		return departmentToAdd;
	}

	/**
	 * @return the departmentToUpdate
	 */
	public Department getDepartmentToUpdate() {
		return departmentToUpdate;
	}

	/**
	 * @param departmentToUpdate the departmentToUpdate to set
	 */
	public void setDepartmentToUpdate(final Department departmentToUpdate) {
		this.departmentToUpdate = new Department(departmentToUpdate);
	}

	/**
	 * @return the departmentManagerToUpdate
	 */
	public DepartmentManager getDepartmentManagerToUpdate() {
		return departmentManagerToUpdate;
	}

	/**
	 * @param departmentManagerToUpdate the departmentManagerToUpdate to set
	 */
	public void setDepartmentManagerToUpdate(
			final DepartmentManager departmentManagerToUpdate) {
		this.departmentManagerToUpdate = new DepartmentManager(departmentManagerToUpdate);
	}

	/**
	 * @return the departmentManagerToAdd
	 */
	public DepartmentManager getDepartmentManagerToAdd() {
		return departmentManagerToAdd;
	}

	/**
	 * @return the ldapUid
	 */
	public String getLdapUid() {
		return ldapUid;
	}

	/**
	 * @param ldapUid the ldapUid to set
	 */
	public void setLdapUid(final String ldapUid) {
		this.ldapUid = ldapUid;
	}

	/**
	 * @param ldapUserService the ldapUserService to set
	 */
	public void setLdapService(final LdapUserService ldapUserService) {
		this.ldapUserService = ldapUserService;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(final Department department) {
		this.department = department;
		departmentManagerPaginator.setDepartment(department);
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the departmentManagerPaginator
	 */
	public DepartmentManagerPaginator getDepartmentManagerPaginator() {
		return departmentManagerPaginator;
	}

	/**
	 * @return the departmentPaginator
	 */
	public ManagedDepartmentPaginator getDepartmentPaginator() {
		return departmentPaginator;
	}

}
