package org.esupportail.formation.web.controllers;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.esupportail.formation.domain.beans.Task;
import org.esupportail.formation.domain.beans.User;
import org.esupportail.formation.utils.TaskDateComparator;
import org.esupportail.formation.utils.TaskTitleComparator;

public class TaskController extends AbstractContextAwareController {

	/**
	 * VersionId
	 */
	private static final long serialVersionUID = -872218604638760392L;
	private List<Task> sortedTasks=null;
	private Task currentTask=null;
	
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
	}

	public String getTestDao(){
		//menage
		supprimeTout();
		
	/*	User user1=new User();
		user1.setLogin("cbissler");
		getDomainService().addUser(user1);
		User user2=new User();
		user2.setLogin("bourges");
		getDomainService().addUser(user2);
		
		
		//On ajoute 4 tâches
		getDomainService().addTask(new Task(true, "titre de tache 1", "", new Date(), user1));
		getDomainService().addTask(new Task(true, "titre de tache 2", "", new Date(), user1));
		getDomainService().addTask(new Task(true, "titre de tache 3", "", new Date(), user1));
		getDomainService().addTask(new Task(true, "titre de tache 4", "", new Date(), user2));
				
		List<Task> liste=getDomainService().getTasks();
		afficheTaches(liste,"avant modification");
		//on modifie la 1ere tâche
		liste.get(0).setTitle("Autre titre");
		getDomainService().updateTask(liste.get(0));
		// on supprime la seconde
		getDomainService().deleteTask(liste.get(1));
		
		List<Task> liste1=getDomainService().getTasks();
		afficheTaches(liste1,"apres modification");
		
		List<Task> liste2=getDomainService().getTasksForUser(user1);
		afficheTaches(liste2 ,"pour le user *"+user1.getLogin()+"*");
		
		//remove user
		User u=getDomainService().getUser("cbissler");
		getDomainService().deleteUser(u);
		
		List<Task> liste3=getDomainService().getTasks();
		afficheTaches(liste3,"apres delete User");*/
		return "OK";
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
	
	public String goToTaskManagerPage(){
		return "go_taskManagerPage";
	}
	public List<Task> getTasks() {
		if (this.sortedTasks==null)
			sortedTasks = getDomainService().getTasks();
		return sortedTasks;
	}
	public void sortTasksByDate() {
		if (this.sortedTasks==null)
			sortedTasks = getDomainService().getTasks();
		Collections.sort(sortedTasks, new TaskDateComparator());
	}
	public void sortTasksByTitle() {
		if (this.sortedTasks==null)
			sortedTasks = getDomainService().getTasks();
		Collections.sort(sortedTasks, new TaskTitleComparator());
	}
	public void addFakeTask() {
		User user1=getDomainService().getUser("cbissler");
		if(user1 == null){
			user1=new User();
			user1.setLogin("cbissler");
			getDomainService().addUser(user1);
		}
		//On ajoute la tâche avec un titre dont les deux premières lettres sont tirées au sort (pour tester le tri)
		getDomainService().addTask(new Task(true, getLettreAuHasard()+""+getLettreAuHasard()+" - Titre de tache bidon", "", new Date(), user1));
		sortedTasks = getDomainService().getTasks();
	}
	private char getLettreAuHasard(){
		Random r = new Random();
	    String alphabet = "abcdefghijklmnopqrstuvwxyz";
		return alphabet.charAt(r.nextInt(alphabet.length()));
	}
	public void supprimeTout() {
		List<Task> listeTask=getDomainService().getTasks();
		for (Task t : listeTask) {
			getDomainService().deleteTask(t);
		}
		List<User> listeUser=getDomainService().getUsers();
		for (User u : listeUser) {
			getDomainService().deleteUser(u);
		}
		sortedTasks = getDomainService().getTasks();
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}
	
}