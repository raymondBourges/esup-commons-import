/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.formation.dao.DaoService;
import org.esupportail.formation.domain.beans.Task;
import org.esupportail.formation.domain.beans.User;
import org.springframework.beans.factory.InitializingBean;

public class DomainServiceImpl implements DomainService, InitializingBean {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = 5562208937407153456L;

	/**
	 * For Logging.
	 */
	@SuppressWarnings("unused")
	private final Logger logger = new LoggerImpl(this.getClass());
	private DaoService daoService;

	
	/**
	 * Constructor.
	 */
	public DomainServiceImpl() {
		super();
		
	}


	///////////////////////////
	// User
	///////////////////////////

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.daoService, 
				"property daoService of class " + this.getClass().getName() + " can not be null");
		//testDao1();
	}
	


	@Override
	public User getUser(String uid) {
		User user = null;
		for (User userInList : getDaoService().getUsers()) {
			if (userInList.getLogin().equals(uid)) {
				user = userInList;
				break;
			}
		}
		if (user == null) {
			user = new User();
			user.setLogin(uid);
			// On cree l'utilisateur, son nom complet prend la valeur de
			// l'Uid.
			user.setDisplayName(uid);
			user.setLanguage("fr");
		}
		return user;
	}
	
	public void addUser(User user){
		User tmp = daoService.getUser(user.getLogin());
		if (tmp == null) { 
			daoService.addUser(user);			
		}
	}
	public void deleteUser(User user){
		daoService.deleteUser(user);
	}
	public List<User> getUsers() {
		return daoService.getUsers();
	}
	
	///////////////////////////
	// Task
	///////////////////////////
	public List<Task> getTasks() {
		return daoService.getTasks();
	}
	
	@Override
	public void addTask(Task task) {
		Task tmp = daoService.getTask(task.getId());
		if (tmp == null) { 
			// task does not already exists in database
			//logger.debug("addTask -> not found "+task.getId());
			System.out.println("addTask -> not found "+task.getId());
			daoService.addTask(task);			
		}
		else {
			System.out.println("addTask -> found "+task.getId());
			daoService.updateTask(task);
			//logger.debug("addTask -> found "+task.getId());
		}
		
	}
	public void deleteTask(Task task) {
		daoService.deleteTask(task);
	}
	public void updateTask(Task task) {
		daoService.updateTask(task);
	}
	@Override
	public List<Task> getTasksForUser(User user) {
		return daoService.getTasksForUser(user);
	}
	
		
	///////////////////////////
	// DaoService
	///////////////////////////
	public DaoService getDaoService() {
		return daoService;
	}

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
    ///////////////////////////
	// Test DaoService
	///////////////////////////
	private void testDao1(){
		Task t=new Task();
		t.setPublicTask(true);
		t.setTitle("titre de tache 1");
		t.setDate(new Date());
		addTask(t);
		
		List<Task> liste=getTasks();
		afficheTaches(liste,"avant modification");
	}
	private void testDao(){
		//menage
		supprimeTout();
		
		User user1=new User();
		user1.setLogin("cbissler");
		addUser(user1);
		User user2=new User();
		user2.setLogin("bourges");
		addUser(user2);
		
		
		//On ajoute 3 tâches
		addTask(new Task(true, "titre de tache 1", "", new Date(), user1));
		addTask(new Task(true, "titre de tache 2", "", new Date(), user1));
		addTask(new Task(true, "titre de tache 3", "", new Date(), user1));
		addTask(new Task(true, "titre de tache 4", "", new Date(), user2));
		
		List<Task> liste=getTasks();
		afficheTaches(liste,"avant modification");
		//on modifie la 1ere tâche
		liste.get(0).setTitle("Autre titre");
		updateTask(liste.get(0));
		// on supprime la seconde
		deleteTask(liste.get(1));
		
		List<Task> liste1=getTasks();
		afficheTaches(liste1,"apres modification");
		
		List<Task> liste2=getTasksForUser(user1);
		afficheTaches(liste2 ,"pour le user *"+user1.getLogin()+"*");
		
		//remove user
		User u=getUser("cbissler");
		deleteUser(u);
		
		List<Task> liste3=getTasks();
		afficheTaches(liste3,"apres delete User");
	}

	private void afficheTaches(List<Task> liste, String info) {
		System.out.println("== Liste des tâches en base "+info+" == ");
		for (Task t : liste) {
			if (t.getOwner()!=null)
				System.out.println(t.getId()+" : "+t.getTitle()+" ( "+t.getOwner().getLogin()+" ) ");
			else
				System.out.println(t.getId()+" : "+t.getTitle());
		}
	}
	private void supprimeTout() {
		List<Task> listeTask=getTasks();
		for (Task t : listeTask) {
			deleteTask(t);
		}
		List<User> listeUser=getUsers();
		for (User u : listeUser) {
			deleteUser(u);
		}
	}
}
