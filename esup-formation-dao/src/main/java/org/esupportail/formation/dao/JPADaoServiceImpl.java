/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.formation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.esupportail.commons.dao.AbstractGenericJPADaoService;
import org.esupportail.formation.domain.beans.Task;
import org.esupportail.formation.domain.beans.User;

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
	// EntityManager
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
	

	//////////////////////////////////////////////////////////////
	// Task
	//////////////////////////////////////////////////////////////
	
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
	@SuppressWarnings("unchecked")
	public List<Task> getTasksForUser(User u) {
		Query q = entityManager.createNamedQuery("tasksForUser");
		q.setParameter("userLogin", u.getLogin());
		List<Task> ret = (List<Task>)q.getResultList();
		return ret;
	} 
	@SuppressWarnings("unchecked")
	public List<Task> get10LastTasksForUser(User u) {
		Query q = entityManager.createNamedQuery("tasksForUser");
		q.setParameter("userLogin", u.getLogin());
		q.setMaxResults(10);
		List<Task> ret = (List<Task>)q.getResultList();
		return ret;
	} 
	@Override
	public void addTask(Task task) {
		entityManager.persist(task);
		
	}

	@Override
	public void deleteTask(Task task) {
		Task t = entityManager.find(Task.class, task.getId());
		entityManager.remove(t);
		
	}

	@Override
	public Task updateTask(Task task) {
		return entityManager.merge(task);
	}

	@Override
	public Task getTask(long id) {
		Task task = entityManager.find(Task.class, id);
		return task;
	}

	
	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Query q = entityManager.createNamedQuery("allUsers");
		List<User> ret = (List<User>)q.getResultList();
		return ret;
	}

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void deleteUser(User user) {
		User u = entityManager.find(User.class, user.getId());
		entityManager.remove(u);
	}

	@Override
	public User updateUser(User user) {
		return entityManager.merge(user);
	}

	@Override
	public User getUser(long id) {
		User user = entityManager.find(User.class, id);
		return user;
	}
	@SuppressWarnings("unchecked")
	public User getUser(String login) {
		Query q = entityManager.createNamedQuery("userByLogin");
		q.setParameter("login", login);
		List<User> ret = (List<User>)q.getResultList();
		if (ret.size()==1)
			return ret.get(0);
		else
			return null;
	}
}