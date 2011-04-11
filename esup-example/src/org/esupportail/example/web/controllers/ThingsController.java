/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.Thing;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.web.beans.ThingPaginator;

/**
 * A bean to manage things.
 */
public class ThingsController extends AbstractContextAwareController {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -757572965637594135L;

	/**
	 * The current department.
	 */
	private Department department;
	
	/**
	 * The thing paginator.
	 */
	private ThingPaginator paginator;
	
	/**
	 * The thing to update.
	 */
	private Thing thingToUpdate;

	/**
	 * Constructor.
	 *
	 */
	public ThingsController() {
		super();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		thingToUpdate = null;
		department = null;
		paginator = new ThingPaginator(getDomainService());
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
		paginator.setDepartment(department);
		return "navigationThings";
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() + "[thingToUpdate=" + thingToUpdate 
		+ ", paginator=" + paginator + "]";
	}

	/**
	 * @return true if the current user is allowed to view the page.
	 */
	public boolean isPageAuthorized() {
		User currentUser = getCurrentUser();
		if (currentUser == null) {
			return false;
		}
		if (currentUser.getAdmin()) {
			return true;
		}
		return getDomainService().isDepartmentManager(currentUser);
	}

	/**
	 * @return The departments managed by the current user, as a list of SelectItem.
	 */
	public List<SelectItem> getDepartmentItems() {
		List<SelectItem> departmentItems = new ArrayList<SelectItem>();
		departmentItems.clear();
		departmentItems.add(new SelectItem("", getString("THINGS.TEXT.DEPARTMENT_SELECTION.DEFAULT")));
		List<Department> departments = getDomainService().getManagedDepartments(getCurrentUser());
		for (Department dep : departments) {
			departmentItems.add(new SelectItem(dep, dep.getLabel()));
		}
		return departmentItems;
	}
	
	/**
	 * @return 'true' if the current user can add things.
	 */
	public boolean isCurrentUserCanAddThing() {
		Department currentDepartment = department;
		if (currentDepartment == null) {
			return false;
		}
		return getDomainService().userCanAddThing(getCurrentUser(), currentDepartment);
	}

	/**
	 * @return 'true' if the current user can view things.
	 */
	public boolean isCurrentUserCanViewThings() {
		return getDomainService().userCanViewThings(getCurrentUser(), department);
	}

	/**
	 * @return 'true' if the current user can delete things.
	 */
	public boolean isCurrentUserCanDeleteThings() {
		return getDomainService().userCanDeleteThings(getCurrentUser(), department);
	}

	/**
	 * @return 'true' if the current user can edit things.
	 */
	public boolean isCurrentUserCanEditThings() {
		return getDomainService().userCanEditThings(getCurrentUser(), department);
	}

	/**
	 * @return a String.
	 */
	public String addThing() {
		if (department == null) {
			addUnauthorizedActionMessage();
			return null;
		}
		if (!isCurrentUserCanAddThing()) {
			addUnauthorizedActionMessage();
			return null;
		}
		Thing thing = getDomainService().addThing(
				department, getCurrentUser(), System.currentTimeMillis());
		paginator.forceReload();
		setThingToUpdate(thing);
		addInfoMessage(null, "THINGS.MESSAGE.THING_ADDED");
		return "thingAdded";
	}

	/**
	 * @return a String.
	 */
	public String updateThing() {
		if (!isCurrentUserCanEditThings()) {
			addUnauthorizedActionMessage();
			return null;
		}
		getDomainService().updateThing(thingToUpdate, getCurrentUser(), System.currentTimeMillis());
		paginator.forceReload();
		addInfoMessage(null, "THINGS.MESSAGE.THING_UPDATED");
		return "thingUpdated";
	}

	/**
	 * @return a String.
	 */
	public String confimDeleteThing() {
		if (!isCurrentUserCanDeleteThings()) {
			addUnauthorizedActionMessage();
			return null;
		}
		getDomainService().deleteThing(thingToUpdate);
		paginator.forceReload();
		addInfoMessage(null, "THINGS.MESSAGE.THING_DELETED");
		return "thingDeleted";
	}

	/**
	 * @return a String.
	 */
	public String moveFirst() {
		getDomainService().moveFirst(thingToUpdate);
		paginator.forceReload();
		return null;
	}
	
	/**
	 * @return a String.
	 */
	public String moveLast() {
		getDomainService().moveLast(thingToUpdate);
		paginator.forceReload();
		return null;
	}
	
	/**
	 * @return a String.
	 */
	public String moveUp() {
		getDomainService().moveUp(thingToUpdate);
		paginator.forceReload();
		return null;
	}
	
	/**
	 * @return a String.
	 */
	public String moveDown() {
		getDomainService().moveDown(thingToUpdate);
		paginator.forceReload();
		return null;
	}
	
	/**
	 * @return the thingToUpdate
	 */
	public Thing getThingToUpdate() {
		return this.thingToUpdate;
	}

	/**
	 * @param thingToUpdate the thingToUpdate to set
	 */
	public void setThingToUpdate(final Thing thingToUpdate) {
		this.thingToUpdate = new Thing(thingToUpdate);
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(final Department department) {
		this.department = department;
		paginator.setDepartment(department);
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the paginator
	 */
	public ThingPaginator getPaginator() {
		return paginator;
	}

}
