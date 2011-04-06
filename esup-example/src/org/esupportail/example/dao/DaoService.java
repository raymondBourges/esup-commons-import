/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.dao;

import java.io.Serializable;
import java.util.List;

import org.esupportail.commons.web.beans.Paginator;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.DepartmentManager;
import org.esupportail.example.domain.beans.Thing;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.beans.VersionManager;

/**
 * The DAO service interface.
 */
public interface DaoService extends Serializable {

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////
	
	/**
	 * @param id
	 * @return the User instance that corresponds to an id.
	 */
	User getUser(String id);

	/**
	 * @return the list of all the users.
	 */
	List<User> getUsers();

	/**
	 * Add a user.
	 * @param user
	 */
	void addUser(User user);

	/**
	 * Delete a user.
	 * @param user
	 */
	void deleteUser(User user);

	/**
	 * Update a user.
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * @return a paginator for administrators.
	 */
	Paginator<User> getAdminPaginator();

	//////////////////////////////////////////////////////////////
	// Department
	//////////////////////////////////////////////////////////////
	
	/**
	 * @param id
	 * @return the Department instance that corresponds to an id.
	 */
	Department getDepartment(final long id);

	/**
	 * @return the list of all the departments.
	 */
	List<Department> getDepartments();

	/**
	 * Add a department.
	 * @param department
	 */
	void addDepartment(final Department department);

	/**
	 * Update a department.
	 * @param department
	 */
	void updateDepartment(final Department department);

	/**
	 * Delete a department.
	 * @param department
	 */
	void deleteDepartment(final Department department);

	/**
	 * @param label
	 * @return 'true' if a department has the same name of label. 
	 */
	boolean isDepartmentLabelUsed(final String label);

	/**
	 * @param department
	 * @return the things of a department.
	 */
	List<Thing> getThings(Department department);
	
	/**
	 * @param department
	 * @return the number of things of a department.
	 */
	int getThingsNumber(Department department);
	
	/**
	 * @param department
	 * @param order
	 * @return the thing of a department that corresponds to an order.
	 */
	Thing getThingByOrder(Department department, int order);
	
	//////////////////////////////////////////////////////////////
	// DepartmentManager
	//////////////////////////////////////////////////////////////
	
	/**
	 * @param department 
	 * @param user 
	 * @return the departmentManager instance that corresponds to a
	 * department and a user, null if not found.
	 */
	DepartmentManager getDepartmentManager(
			final Department department, 
			final User user);

	/**
	 * @param department 
	 * @return the managers of a department
	 */
	List<DepartmentManager> getDepartmentManagers(final Department department);

	/**
	 * Add a department manager.
	 * @param departmentManager
	 */
	void addDepartmentManager(final DepartmentManager departmentManager);

	/**
	 * Update a departmentManager. (manage managers).
	 * @param departmentManager
	 */
	void updateDepartmentManager(final DepartmentManager departmentManager);

	/**
	 * Delete a department manager.
	 * @param departmentManager
	 */
	void deleteDepartmentManager(final DepartmentManager departmentManager);

	/**
	 * @param user 
	 * @return the instances of DepartmentManager that corresponds to the user.
	 */
	List<DepartmentManager> getDepartmentManagers(User user);

	//////////////////////////////////////////////////////////////
	// Thing
	//////////////////////////////////////////////////////////////
	
	/**
	 * Add a thing.
	 * @param thing
	 */
	void addThing(Thing thing);

	/**
	 * Update a thing.
	 * @param thing
	 */
	void updateThing(Thing thing);

	/**
	 * Delete a thing.
	 * @param thing
	 */
	void deleteThing(Thing thing);

	//////////////////////////////////////////////////////////////
	// VersionManager
	//////////////////////////////////////////////////////////////
	
	/**
	 * @return the VersionManager of the database.
	 */
	VersionManager getVersionManager();

	/**
	 * Update a VersionManager.
	 * @param versionManager
	 */
	void updateVersionManager(VersionManager versionManager);

}
