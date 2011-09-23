package org.esupportail.formation.web.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

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
	private Task taskToEditOrDelete=null;
	
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		currentTask = new Task();
		taskToEditOrDelete = new Task();
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
	
	public void addTask() {
		getDomainService().addTask(currentTask);
		currentTask=new Task();
		sortedTasks = getDomainService().getTasks();
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	
	public List<SelectItem> getUserItems() {
		List<User> les_users= getDomainService().getUsers();
		ArrayList<SelectItem> userItems = new ArrayList<SelectItem>();
		for (User user : les_users) {
			if (user.getDisplayName() !=null)
				userItems.add(new SelectItem(user, user.getDisplayName()+"("+user.getLogin()+")"));
			else
				userItems.add(new SelectItem(user, "Inconnu ("+user.getLogin()+")"));
		}		
		return userItems;
	}
	public void validateTitle(FacesContext ctx, UIComponent componentToValidate, Object obj)
	throws ValidatorException {
		if (obj != null){
			if (((String)obj).length()<5)
				throw new ValidatorException(new FacesMessage("Erreur le titre ne doit pas comporter moins de 5 caractères"));
		}
	}
	
	public void deleteTask() {
		getDomainService().deleteTask(currentTask);
		taskToEditOrDelete=new Task();
		sortedTasks = getDomainService().getTasks();
	}
	public void editTask() {
		currentTask = taskToEditOrDelete;
		taskToEditOrDelete=new Task();
		sortedTasks = getDomainService().getTasks();
	}

	public Task getTaskToEditOrDelete() {
		return taskToEditOrDelete;
	}

	public void setTaskToEditOrDelete(Task taskToEditOrDelete) {
		this.taskToEditOrDelete = taskToEditOrDelete;
	}
	
	
}