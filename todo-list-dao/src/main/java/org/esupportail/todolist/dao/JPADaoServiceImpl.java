/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.esupportail.commons.dao.AbstractGenericJPADaoService;
import org.esupportail.todolist.domain.beans.Information;
import org.esupportail.todolist.domain.beans.Task;
import org.esupportail.todolist.domain.beans.User;

/**
 * The Hiberate implementation of the DAO service.
 */
public class JPADaoServiceImpl extends AbstractGenericJPADaoService implements DaoService {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 3152554337896617315L;
	
	/**
	 * JPA entity manager
	 */
	EntityManager entityManager;

	/**
	 * Bean constructor.
	 */
	public JPADaoServiceImpl() {
		super();
	}
	
	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		
	}

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////

	/**
	 * @param em the em to set
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	/**
	 * @see org.esupportail.commons.dao.AbstractGenericJPADaoService#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * @see org.esupportail.todolist.dao.DaoService#getUser(java.lang.String)
	 */
	public User getUser(final String id) {
		User u = entityManager.find(User.class, id);
		return u;
	}

	/**
	 * @see org.esupportail.todolist.dao.DaoService#getUsers()
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Query q = entityManager.createQuery("SELECT user FROM User user");
		List<User> ret = (List<User>)q.getResultList();
		return ret;
	}
	
	
	/**
	 * @see org.esupportail.todolist.dao.DaoService#addUser(org.esupportail.todolist.domain.beans.User)
	 */
	public void addUser(final User user) {
		List<Information> informations = new ArrayList<Information>();
		Information information = new Information();
		information.setInformationKey("INSERT_DATE");
		information.setInformationValue(getDateTime());
		informations.add(information);
		user.setInformations(informations);
		entityManager.persist(user);
	}

	/**
	 * @see org.esupportail.todolist.dao.DaoService#deleteUser(org.esupportail.todolist.domain.beans.User)
	 */
	public void deleteUser(final User user) {
		User tmp = entityManager.find(User.class, user.getId());
		entityManager.remove(tmp);
	}

	/**
	 * @see org.esupportail.todolist.dao.DaoService#updateUser(org.esupportail.todolist.domain.beans.User)
	 */
	public void updateUser(final User user) {
		List<Information> informations = user.getInformations();
		if (informations == null) {
			informations = new ArrayList<Information>();
		}
		Information information = new Information();
		information.setInformationKey("UPDATE_DATE");
		information.setInformationValue(getDateTime());
		informations.add(information);
		user.setInformations(informations);
		entityManager.merge(user);
	}

	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
	
	
	//////////////////////////////////////////////////////////////
	// Task
	//////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public List<Task> getTasksForUser(User u) {
		Query q = entityManager.createNamedQuery("tasksForUser");
		q.setParameter("userId", u.getId());
		List<Task> ret = (List<Task>)q.getResultList();
		return ret;
	}
	@SuppressWarnings("unchecked")
	public List<Task> getPublicTasks() {
		Query q = entityManager.createNamedQuery("publicTasks");
		List<Task> ret = (List<Task>)q.getResultList();
		return ret;
	}
	@SuppressWarnings("unchecked")
	public List<Task> getTasks() {
		Query q = entityManager.createNamedQuery("allTasks");
		List<Task> ret = (List<Task>)q.getResultList();
		return ret;
	}

	@Override
	public void addTask(Task task) {
		entityManager.persist(task);
		
	}

	@Override
	public void deleteTask(Task task) {
		entityManager.remove(task);
		
	}

	@Override
	public void updateTask(Task task) {
		entityManager.merge(task);
	}

	@Override
	public Task getTask(long id) {
		Task task = entityManager.find(Task.class, id);
		return task;
	}
	
	//////////////////////////////////////////////////////////////
	// misc
	//////////////////////////////////////////////////////////////
}
