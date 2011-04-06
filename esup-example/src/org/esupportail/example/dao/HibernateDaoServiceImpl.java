/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.esupportail.commons.dao.AbstractJdbcJndiHibernateDaoService;
import org.esupportail.commons.dao.HibernateFixedQueryPaginator;
import org.esupportail.commons.dao.HqlUtils;
import org.esupportail.commons.services.application.UninitializedDatabaseException;
import org.esupportail.commons.services.application.VersionningUtils;
import org.esupportail.commons.web.beans.Paginator;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.DepartmentManager;
import org.esupportail.example.domain.beans.Thing;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.beans.VersionManager;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.BadSqlGrammarException;

/**
 * The Hiberate implementation of the DAO service.
 * 
 * See /properties/dao/dao-example.xml
 */
public class HibernateDaoServiceImpl 
extends AbstractJdbcJndiHibernateDaoService
implements DaoService, InitializingBean {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 5070824440073876000L;

	/**
	 * The name of the 'id' attribute.
	 */
	private static final String ID_ATTRIBUTE = "id";

	/**
	 * The name of the 'order' attribute.
	 */
	private static final String ORDER_ATTRIBUTE = "order";

	/**
	 * The name of the 'user' attribute.
	 */
	private static final String USER_ATTRIBUTE = "user";

	/**
	 * The name of the 'admin' attribute.
	 */
	private static final String ADMIN_ATTRIBUTE = "admin";

	/**
	 * The name of the 'department' attribute.
	 */
	private static final String DEPARTMENT_ATTRIBUTE = "department";

	/**
	 * The name of the 'label' attribute.
	 */
	private static final String LABEL_ATTRIBUTE = "label";

	/**
	 * Bean constructor.
	 */
	public HibernateDaoServiceImpl() {
		super();
	}

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.dao.DaoService#getUser(java.lang.String)
	 */
	public User getUser(final String id) {
		return (User) getHibernateTemplate().get(User.class, id);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getUsers()
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return getHibernateTemplate().loadAll(User.class);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#addUser(org.esupportail.example.domain.beans.User)
	 */
	public void addUser(final User user) {
		addObject(user);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#deleteUser(org.esupportail.example.domain.beans.User)
	 */
	public void deleteUser(final User user) {
		deleteObject(user);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#updateUser(org.esupportail.example.domain.beans.User)
	 */
	public void updateUser(final User user) {
		updateObject(user);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getAdminPaginator()
	 */
	public Paginator<User> getAdminPaginator() {
		String queryStr = HqlUtils.fromWhereOrderByAsc(
				User.class.getSimpleName(),
				HqlUtils.isTrue(ADMIN_ATTRIBUTE),
				ID_ATTRIBUTE);
		return new HibernateFixedQueryPaginator<User>(this, queryStr);
	}

	//////////////////////////////////////////////////////////////
	// Department
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.dao.DaoService#getDepartment(long)
	 */
	public Department getDepartment(final long id) {
		return (Department) this.getHibernateTemplate().get(Department.class, id);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getDepartments()
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getDepartments() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		criteria.addOrder(Order.asc(ID_ATTRIBUTE));
		return  getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#addDepartment(org.esupportail.example.domain.beans.Department)
	 */
	public void addDepartment(final Department department) {
		addObject(department);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#updateDepartment(org.esupportail.example.domain.beans.Department)
	 */
	public void updateDepartment(final Department department) {
		updateObject(department);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#deleteDepartment(org.esupportail.example.domain.beans.Department)
	 */
	public void deleteDepartment(final Department department) {
		deleteObjects(getThings(department));
		deleteObjects(getDepartmentManagers(department));
		deleteObject(department);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#isDepartmentLabelUsed(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean isDepartmentLabelUsed(final String label) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		criteria.add(Restrictions.eq(LABEL_ATTRIBUTE, label));
		List<Department> result = getHibernateTemplate().findByCriteria(criteria);
		return result.size() > 0;
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getThings(org.esupportail.example.domain.beans.Department)
	 */
	@SuppressWarnings("unchecked")
	public List<Thing> getThings(
			final Department department) {
		if (department == null) {
			return new ArrayList<Thing>();
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Thing.class);
		criteria.addOrder(Order.asc(ORDER_ATTRIBUTE));
		criteria.add(Restrictions.eq(DEPARTMENT_ATTRIBUTE, department));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getThingsNumber(org.esupportail.example.domain.beans.Department)
	 */
	public int getThingsNumber(final Department department) {
		return getQueryIntResult(
				"select count(*) from Thing where department.id = " + department.getId());
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getThingByOrder(
	 * org.esupportail.example.domain.beans.Department, int)
	 */
	@SuppressWarnings("unchecked")
	public Thing getThingByOrder(final Department department, final int order) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Thing.class);
		criteria.add(Restrictions.eq(DEPARTMENT_ATTRIBUTE, department));
		criteria.add(Restrictions.eq(ORDER_ATTRIBUTE, new Integer(order)));
		List<Thing> things = getHibernateTemplate().findByCriteria(criteria);  
		if (things.isEmpty()) {
			return null;
		}
		return things.get(0);
	}

	//////////////////////////////////////////////////////////////
	// DepartmentManager
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.dao.DaoService#getDepartmentManager(
	 * org.esupportail.example.domain.beans.Department, org.esupportail.example.domain.beans.User)
	 */
	@SuppressWarnings("unchecked")
	public DepartmentManager getDepartmentManager(
			final Department department, 
			final User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(DepartmentManager.class);
		criteria.add(Restrictions.eq(DEPARTMENT_ATTRIBUTE, department));
		criteria.add(Restrictions.eq(USER_ATTRIBUTE, user));
		List<DepartmentManager> managers = getHibernateTemplate().findByCriteria(criteria);
		if (managers.isEmpty()) {
			return null;
		}
		return managers.get(0);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getDepartmentManagers(
	 * org.esupportail.example.domain.beans.Department)
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentManager> getDepartmentManagers(
			final Department department) {
		if (department == null) {
			return new ArrayList<DepartmentManager>();
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(DepartmentManager.class);
		criteria.addOrder(Order.asc(ID_ATTRIBUTE));
		criteria.add(Restrictions.eq(DEPARTMENT_ATTRIBUTE, department));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#addDepartmentManager(
	 * org.esupportail.example.domain.beans.DepartmentManager)
	 */
	public void addDepartmentManager(
			final DepartmentManager departmentManager) {
		addObject(departmentManager);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#updateDepartmentManager(
	 * org.esupportail.example.domain.beans.DepartmentManager)
	 */
	public void updateDepartmentManager(
			final DepartmentManager departmentManager) {
		updateObject(departmentManager);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#deleteDepartmentManager(
	 * org.esupportail.example.domain.beans.DepartmentManager)
	 */
	public void deleteDepartmentManager(
			final DepartmentManager departmentManager) {
		deleteObject(departmentManager);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getDepartmentManagers(org.esupportail.example.domain.beans.User)
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentManager> getDepartmentManagers(final User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(DepartmentManager.class);
		criteria.addOrder(Order.asc(ID_ATTRIBUTE));
		criteria.add(Restrictions.eq(USER_ATTRIBUTE, user));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	//////////////////////////////////////////////////////////////
	// Thing
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.dao.DaoService#addThing(org.esupportail.example.domain.beans.Thing)
	 */
	public void addThing(final Thing thing) {
		addObject(thing);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#updateThing(org.esupportail.example.domain.beans.Thing)
	 */
	public void updateThing(final Thing thing) {
		updateObject(thing);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#deleteThing(org.esupportail.example.domain.beans.Thing)
	 */
	public void deleteThing(final Thing thing) {
		deleteObject(thing);
	}

	//////////////////////////////////////////////////////////////
	// VersionManager
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.dao.DaoService#getVersionManager()
	 */
	@SuppressWarnings("unchecked")
	public VersionManager getVersionManager() {
		DetachedCriteria criteria = DetachedCriteria.forClass(VersionManager.class);
		criteria.addOrder(Order.asc(ID_ATTRIBUTE));
		List<VersionManager> versionManagers;
		try {
			versionManagers = getHibernateTemplate().findByCriteria(criteria);
		} catch (BadSqlGrammarException e) {
			throw new UninitializedDatabaseException(
					"your database is not initialized, please run 'ant init-data'", e);
		}
		if (versionManagers.isEmpty()) {
			VersionManager versionManager = new VersionManager();
			versionManager.setVersion(VersionningUtils.VERSION_0);
			addObject(versionManager);
			return versionManager;
		}
		return versionManagers.get(0);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#updateVersionManager(
	 * org.esupportail.example.domain.beans.VersionManager)
	 */
	public void updateVersionManager(final VersionManager versionManager) {
		updateObject(versionManager);
	}

	//////////////////////////////////////////////////////////////
	// misc
	//////////////////////////////////////////////////////////////

}
