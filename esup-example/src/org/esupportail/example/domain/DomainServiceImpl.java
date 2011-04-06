/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.esupportail.commons.exceptions.ConfigException;
import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.commons.services.application.Version;
import org.esupportail.commons.services.ldap.LdapUser;
import org.esupportail.commons.services.ldap.LdapUserService;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.web.beans.Paginator;
import org.esupportail.example.dao.DaoService;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.DepartmentManager;
import org.esupportail.example.domain.beans.Thing;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.beans.VersionManager;
import org.esupportail.example.exceptions.DepartmentManagerNotFoundException;
import org.esupportail.example.exceptions.DepartmentNotFoundException;
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
	private static final long serialVersionUID = 315513827023468900L;

	/**
	 * {@link DaoService}.
	 */
	private DaoService daoService;

	/**
	 * {@link LdapUserService}.
	 */
	private LdapUserService ldapUserService;

	/**
	 * The LDAP attribute that contains the display name. 
	 */
	private String displayNameLdapAttribute;
	
	/**
	 * The upload directory.
	 */
	private String uploadDirectory;

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
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.daoService, 
				"property daoService of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(this.ldapUserService, 
				"property ldapUserService of class " + this.getClass().getName() + " can not be null");
		Assert.hasText(this.displayNameLdapAttribute, 
				"property displayNameLdapAttribute of class " + this.getClass().getName() 
				+ " can not be null");
		Assert.hasText(this.uploadDirectory, 
				"property uploadDirectory of class " + this.getClass().getName() + " can not be null");
	}

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////

	/**
	 * Set the information of a user from a ldapUser.
	 * @param user 
	 * @param ldapUser 
	 * @return true if the user was updated.
	 */
	private boolean setUserInfo(
			final User user, 
			final LdapUser ldapUser) {
		String displayName = null;
		List<String> displayNameLdapAttributes = ldapUser.getAttributes().get(displayNameLdapAttribute);
		if (displayNameLdapAttributes != null) {
			displayName = displayNameLdapAttributes.get(0);
		}
		if (displayName == null) {
			displayName = user.getId();
		}
		if (displayName.equals(user.getDisplayName())) {
			return false;
		}
		user.setDisplayName(displayName);
		return true;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#updateUserInfo(org.esupportail.example.domain.beans.User)
	 */
	public void updateUserInfo(final User user) {
		if (setUserInfo(user, ldapUserService.getLdapUser(user.getId()))) {
			updateUser(user);
		}
	}

	/**
	 * If the user is not found in the database, try to create it from a LDAP search.
	 * @see org.esupportail.example.domain.DomainService#getUser(java.lang.String)
	 */
	public User getUser(final String id) throws UserNotFoundException {
		User user = daoService.getUser(id);
		if (user == null) {
			LdapUser ldapUser = this.ldapUserService.getLdapUser(id);
			user = new User();
			user.setId(ldapUser.getId());
			setUserInfo(user, ldapUser);
			daoService.addUser(user);
			logger.info("user '" + user.getId() + "' has been added to the database");
		}
		return user;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#getUsers()
	 */
	public List<User> getUsers() {
		return this.daoService.getUsers();
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#updateUser(org.esupportail.example.domain.beans.User)
	 */
	public void updateUser(final User user) {
		this.daoService.updateUser(user);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#addAdmin(org.esupportail.example.domain.beans.User)
	 */
	public void addAdmin(
			final User user) {
		user.setAdmin(true);
		updateUser(user);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#deleteAdmin(org.esupportail.example.domain.beans.User)
	 */
	public void deleteAdmin(
			final User user) {
		user.setAdmin(false);
		updateUser(user);
	}
	
	/**
	 * @see org.esupportail.example.domain.DomainService#getAdminPaginator()
	 */
	public Paginator<User> getAdminPaginator() {
		return this.daoService.getAdminPaginator();
	}

	/**
	 * @param displayNameLdapAttribute the displayNameLdapAttribute to set
	 */
	public void setDisplayNameLdapAttribute(final String displayNameLdapAttribute) {
		this.displayNameLdapAttribute = displayNameLdapAttribute;
	}

	//////////////////////////////////////////////////////////////
	// Department
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.domain.DomainService#getDepartment(long)
	 */
	public Department getDepartment(final long id) 
	throws DepartmentNotFoundException {
		Department department = this.daoService.getDepartment(id); 
		if (department == null) {
			throw new DepartmentNotFoundException("no department found with id [" + id + "]");
		}
		return department;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#getDepartments()
	 */
	public List<Department> getDepartments() {
		return this.daoService.getDepartments();
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#addDepartment(
	 * org.esupportail.example.domain.beans.Department)
	 */
	public void addDepartment(final Department department) {
		this.daoService.addDepartment(department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#updateDepartment(
	 * org.esupportail.example.domain.beans.Department)
	 */
	public void updateDepartment(
			final Department department) {
		this.daoService.updateDepartment(department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#deleteDepartment(
	 * org.esupportail.example.domain.beans.Department)
	 */
	public void deleteDepartment(
			final Department department) {
		this.daoService.deleteDepartment(department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#isDepartmentLabelUsed(java.lang.String)
	 */
	public boolean isDepartmentLabelUsed(final String label) {
		return this.daoService.isDepartmentLabelUsed(label);
	}

	//////////////////////////////////////////////////////////////
	// DepartmentManager
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.domain.DomainService#getDepartmentManager(
	 * org.esupportail.example.domain.beans.Department, org.esupportail.example.domain.beans.User)
	 */
	public DepartmentManager getDepartmentManager(
			final Department department,
			final User user) 
	throws DepartmentManagerNotFoundException {
		DepartmentManager manager = daoService.getDepartmentManager(department, user);
		if (manager == null) {
			throw new DepartmentManagerNotFoundException("user [" + user.getId() 
					+ "] is not a manager of department [" + department.getLabel() + "]");
		}
		return manager;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#isDepartmentManager(
	 * org.esupportail.example.domain.beans.Department, org.esupportail.example.domain.beans.User)
	 */
	public boolean isDepartmentManager(
			final Department department, 
			final User user) {
		try {
			getDepartmentManager(department, user);
		} catch (DepartmentManagerNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#getDepartmentManagers(
	 * org.esupportail.example.domain.beans.Department)
	 */
	public List<DepartmentManager> getDepartmentManagers(
			final Department department) {
		return this.daoService.getDepartmentManagers(department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#addDepartmentManager(
	 * org.esupportail.example.domain.beans.DepartmentManager)
	 */
	public void addDepartmentManager(
			final DepartmentManager departmentManager) {
		this.daoService.addDepartmentManager(departmentManager); 
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#deleteDepartmentManager(
	 * org.esupportail.example.domain.beans.DepartmentManager)
	 */
	public void deleteDepartmentManager(
			final DepartmentManager departmentManager) {
		this.daoService.deleteDepartmentManager(departmentManager);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#updateDepartmentManager(
	 * org.esupportail.example.domain.beans.DepartmentManager)
	 */
	public void updateDepartmentManager(
			final DepartmentManager departmentManager) {
		this.daoService.updateDepartmentManager(departmentManager);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#getManagedDepartments(
	 * org.esupportail.example.domain.beans.User)
	 */
	public List<Department> getManagedDepartments(final User user) {
		if (user == null) {
			return new ArrayList<Department>();
		}
		if (user.getAdmin()) {
			return getDepartments();
		}
		List<DepartmentManager> managers = daoService.getDepartmentManagers(user);
		List<Department> departments = new ArrayList<Department>();
		for (DepartmentManager manager : managers) {
			departments.add(manager.getDepartment());
		}
		return departments;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#isDepartmentManager(
	 * org.esupportail.example.domain.beans.User)
	 */
	public boolean isDepartmentManager(final User user) {
		List<DepartmentManager> managers = daoService.getDepartmentManagers(user);
		return !managers.isEmpty();
	}

	//////////////////////////////////////////////////////////////
	// Thing
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.domain.DomainService#getThings(org.esupportail.example.domain.beans.Department)
	 */
	public List<Thing> getThings(
			final Department department) {
		return this.daoService.getThings(department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#addThing(
	 * org.esupportail.example.domain.beans.Department, org.esupportail.example.domain.beans.User, long)
	 */
	public Thing addThing(
			final Department department,
			final User user,
			final long date) {
		Thing thing = new Thing();
		thing.setDepartment(department);
		thing.setUser(user);
		thing.setDate(new Timestamp(date));
		thing.setOrder(daoService.getThingsNumber(department));
		this.daoService.addThing(thing);
		return thing;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#updateThing(
	 * org.esupportail.example.domain.beans.Thing, org.esupportail.example.domain.beans.User, long)
	 */
	public void updateThing(
			final Thing thing,
			final User user,
			final long date) {
		if (user != null) {
			thing.setUser(user);
			thing.setDate(new Timestamp(date));
		}
		this.daoService.updateThing(thing);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#deleteThing(org.esupportail.example.domain.beans.Thing)
	 */
	public void deleteThing(
			final Thing thing) {
		this.daoService.deleteThing(thing);
	}

//	/**
//	 * Reorder the things of a department.
//	 * @param department
//	 */
//	private void reorderThings(final Department department) {
//		List<Thing> things = getThings(department);
//		int i = 0;
//		for (Thing thing : things) {
//			thing.setOrder(i++);
//			updateThing(thing, null, -1);
//		}
//	}

	/**
	 * @see org.esupportail.example.domain.DomainService#moveFirst(org.esupportail.example.domain.beans.Thing)
	 */
	public void moveFirst(final Thing thingToMove) {
		List<Thing> things = getThings(thingToMove.getDepartment());
		for (Thing thing : things) {
			if (thing.getOrder() < thingToMove.getOrder()) {
				thing.setOrder(thing.getOrder() + 1);
				updateThing(thing, null, -1);
			}
		}
		thingToMove.setOrder(0);
		updateThing(thingToMove, null, -1);
	}
	
	/**
	 * @see org.esupportail.example.domain.DomainService#moveLast(org.esupportail.example.domain.beans.Thing)
	 */
	public void moveLast(final Thing thingToMove) {
		List<Thing> things = getThings(thingToMove.getDepartment());
		for (Thing thing : things) {
			if (thing.getOrder() > thingToMove.getOrder()) {
				thing.setOrder(thing.getOrder() - 1);
				updateThing(thing, null, -1);
			}
		}
		thingToMove.setOrder(things.size() - 1);
		updateThing(thingToMove, null, -1);
	}
	
	/**
	 * @see org.esupportail.example.domain.DomainService#moveUp(org.esupportail.example.domain.beans.Thing)
	 */
	public void moveUp(final Thing thing) {
		Thing previousThing = daoService.getThingByOrder(thing.getDepartment(), thing.getOrder() - 1);
		if (previousThing != null) {
			thing.setOrder(thing.getOrder() - 1);
			updateThing(thing, null, -1);
			previousThing.setOrder(previousThing.getOrder() + 1);
			updateThing(previousThing, null, -1);
		}
	}
	
	/**
	 * @see org.esupportail.example.domain.DomainService#moveDown(org.esupportail.example.domain.beans.Thing)
	 */
	public void moveDown(final Thing thing) {
		Thing nextThing = daoService.getThingByOrder(thing.getDepartment(), thing.getOrder() + 1);
		if (nextThing != null) {
			thing.setOrder(thing.getOrder() + 1);
			updateThing(thing, null, -1);
			nextThing.setOrder(nextThing.getOrder() - 1);
			updateThing(nextThing, null, -1);
		}
	}
	
	//////////////////////////////////////////////////////////////
	// VersionManager
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.domain.DomainService#getDatabaseVersion()
	 */
	public Version getDatabaseVersion() throws ConfigException {
		VersionManager versionManager = daoService.getVersionManager();
		if (versionManager == null) {
			return null;
		}
		return new Version(versionManager.getVersion());
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#setDatabaseVersion(java.lang.String)
	 */
	public void setDatabaseVersion(final String version) {
		if (logger.isDebugEnabled()) {
			logger.debug("setting database version to '" + version + "'...");
		}
		VersionManager versionManager = daoService.getVersionManager();
		versionManager.setVersion(version);
		daoService.updateVersionManager(versionManager);
		if (logger.isDebugEnabled()) {
			logger.debug("database version set to '" + version + "'.");
		}
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#setDatabaseVersion(
	 * org.esupportail.commons.services.application.Version)
	 */
	public void setDatabaseVersion(final Version version) {
		setDatabaseVersion(version.toString());
	}

	//////////////////////////////////////////////////////////////
	// Files
	//////////////////////////////////////////////////////////////
	
	/**
	 * @see org.esupportail.example.domain.DomainService#getUploadDirectory()
	 */
	public String getUploadDirectory() {
		return uploadDirectory;
	}

	/**
	 * @param uploadDirectory the uploadDirectory to set
	 */
	public void setUploadDirectory(final String uploadDirectory) {
		this.uploadDirectory = uploadDirectory;
	}

	//////////////////////////////////////////////////////////////
	// Authorizations
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanViewAdmins(
	 * org.esupportail.example.domain.beans.User)
	 */
	public boolean userCanViewAdmins(final User user) {
		if (user == null) {
			return false;
		}
		return user.getAdmin() || isDepartmentManager(user);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanAddAdmin(org.esupportail.example.domain.beans.User)
	 */
	public boolean userCanAddAdmin(final User user) {
		if (user == null) {
			return false;
		}
		return user.getAdmin();
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanDeleteAdmin(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.User)
	 */
	public boolean userCanDeleteAdmin(final User user, final User admin) {
		if (user == null) {
			return false;
		}
		if (!user.getAdmin()) {
			return false;
		}
		return !user.equals(admin);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanAddDepartment(
	 * org.esupportail.example.domain.beans.User)
	 */
	public boolean userCanAddDepartment(final User user) {
		if (user == null) {
			return false;
		}
		return user.getAdmin();
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanDeleteDepartment(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanDeleteDepartment(
			final User user,
			@SuppressWarnings("unused") final Department department) {
		if (user == null) {
			return false;
		}
		return user.getAdmin();
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanViewDepartment(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanViewDepartment(
			final User user, 
			final Department department) {
		if (user == null) {
			return false;
		}
		if (user.getAdmin()) {
			return true;
		}
		return isDepartmentManager(department, user);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#getVisibleDepartments(
	 * org.esupportail.example.domain.beans.User)
	 */
	public List<Department> getVisibleDepartments(
			final User user) {
		List<Department> result = new ArrayList<Department>(); 
		if (user == null) {
			return result;
		}
		for (Department department : getDepartments()) {
			if (department.getLdapFilter() == null) {
				result.add(department);
			} else if (ldapUserService.userMatchesFilter(user.getId(), department.getLdapFilter())) {
				result.add(department);
			}
		}
		return result;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanEditDepartmentProperties(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanEditDepartmentProperties(
			final User user, 
			final Department department) {
		if (user == null) {
			return false;
		}
		if (user.getAdmin()) {
			return true;
		}
		try {
			DepartmentManager manager = getDepartmentManager(department, user);
			return manager.getManageDepartment();
		} catch (DepartmentManagerNotFoundException e) {
			return false;
		}
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanEditDepartmentManagers(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanEditDepartmentManagers(
			final User user, 
			final Department department) {
		if (user == null) {
			return false;
		}
		if (user.getAdmin()) {
			return true;
		}
		try {
			DepartmentManager manager = getDepartmentManager(department, user);
			return manager.getManageManagers();
		} catch (DepartmentManagerNotFoundException e) {
			return false;
		}
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanAddDepartmentManager(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanAddDepartmentManager(
			final User user, 
			final Department department) {
		return userCanEditDepartmentManagers(user, department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanDeleteManager(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.DepartmentManager)
	 */
	public boolean userCanDeleteManager(
			final User user,
			final DepartmentManager departmentManager) {
		return userCanEditDepartmentManagers(user, departmentManager.getDepartment());
	}

	/**
	 * @param user 
	 * @param department
	 * @return @return 'true' if the user can manage the things fo a department.
	 */
	private boolean userCanManageThings(final User user, final Department department) {
		if (user == null) {
			return false;
		}
		try {
			DepartmentManager manager = getDepartmentManager(department, user);
			return manager.getManageThings();
		} catch (DepartmentManagerNotFoundException e) {
			// not allowed
		}
		return false;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanAddThing(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanAddThing(final User user, final Department department) {
		return userCanManageThings(user, department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanEditThings(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanEditThings(final User user, final Department department) {
		return userCanManageThings(user, department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanDeleteThings(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanDeleteThings(final User user, final Department department) {
		return userCanManageThings(user, department);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#userCanViewThings(
	 * org.esupportail.example.domain.beans.User, org.esupportail.example.domain.beans.Department)
	 */
	public boolean userCanViewThings(final User user, final Department department) {
		return userCanViewDepartment(user, department);
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

	/**
	 * @param ldapUserService the ldapUserService to set
	 */
	public void setLdapUserService(final LdapUserService ldapUserService) {
		this.ldapUserService = ldapUserService;
	}

}
