package org.esupportail.formation.web.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.esupportail.commons.services.smtp.SmtpService;
import org.esupportail.commons.services.urlGeneration.UrlGenerator;
import org.esupportail.formation.domain.beans.Task;
import org.esupportail.formation.domain.beans.User;
import org.esupportail.formation.services.auth.Authenticator;
import org.esupportail.formation.utils.TaskDateComparator;
import org.esupportail.formation.utils.TaskTitleComparator;

import org.esupportail.formation.web.exceptions.TaskException;

public class TaskController extends AbstractContextAwareController {

	/**
	 * VersionId
	 */
	private static final long serialVersionUID = -872218604638760392L;
	private List<Task> sortedTasks=null;
	private Task currentTask=null;
	private Task taskToEditOrDelete=null;
	
	private Authenticator authenticator;
	private SmtpService smtpService;
	
	private UrlGenerator urlGenerator;
	
	private long taskId;
	private Task detailledTask=null;
	
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		currentTask = new Task();
		taskToEditOrDelete = new Task();
	}
	
	public String goToTaskManagerPage(){
		return "go_taskManagerPage";
	}
	public String goToDetailPage(){
		return "go_taskDetailPage";
	}
	private List<Task> getTasksFromDomainService() {
		try {
			if(authenticator.getUser()!=null)
				return getDomainService().getTasksForUser(authenticator.getUser());
			else
				return getDomainService().getTasks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Task> getTasks() {
		if (this.sortedTasks==null){
			sortedTasks = getTasksFromDomainService();
		}		
		return sortedTasks;
	}
	public List<Task> getPublicTasks() {
		return getDomainService().getPublicTasks();
	}
	public void sortTasksByDate() {
		if (this.sortedTasks==null)
			sortedTasks = getTasksFromDomainService();
		Collections.sort(sortedTasks, new TaskDateComparator());
	}
	public void sortTasksByTitle() {
		if (this.sortedTasks==null)
			sortedTasks = getTasksFromDomainService();
		Collections.sort(sortedTasks, new TaskTitleComparator());
	}
	public void addFakeTask() {
		User user1=getDomainService().getUser("tartempion");
		if(user1 == null){
			user1=new User();
			user1.setLogin("tartempion");
			getDomainService().addUser(user1);
		}
		//On ajoute la tâche avec un titre dont les deux premières lettres sont tirées au sort (pour tester le tri)
		getDomainService().addTask(new Task(true, getLettreAuHasard()+""+getLettreAuHasard()+" - Titre de tache bidon", "", new Date(), user1));
		sortedTasks = getTasksFromDomainService();
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
		sortedTasks = getTasksFromDomainService();
	}
	
	public void addTask() {
		System.out.println("ADD"+currentTask.getId());
		getDomainService().addTask(currentTask);
		try {
			smtpService.send(new InternetAddress("celine.didier@uhp-nancy.fr"),
					"CREATION D'UNE TACHE", 
					"<b>Une nouvelle tâche vient d'être crée</b><br/>"+ currentTask.getTitle()+"<br/>"+currentTask.getDescription(), 
					"**Une nouvelle tâche vient d'être crée**\n\n"+ currentTask.getTitle()+"\n"+currentTask.getDescription());
		} catch (AddressException e) {
			
		}
		currentTask=new Task();
		sortedTasks = getTasksFromDomainService();
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
		getDomainService().deleteTask(taskToEditOrDelete);
		taskToEditOrDelete=new Task();
		sortedTasks = getTasksFromDomainService();
	}
	public void editTask() {
		currentTask = taskToEditOrDelete;
		taskToEditOrDelete=new Task();
		sortedTasks = getTasksFromDomainService();
	}

	public Task getTaskToEditOrDelete() {
		return taskToEditOrDelete;
	}

	public void setTaskToEditOrDelete(Task taskToEditOrDelete) {
		this.taskToEditOrDelete = taskToEditOrDelete;
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
		detailledTask = getTaskFromDomainService(taskId);
	}
	public Task getTaskFromDomainService(long tId) {
		return getDomainService().getTask(tId);
	}

	public Task getDetailledTask() {
		return detailledTask;
	}

	public void setDetailledTask(Task detailledTask) {
		this.detailledTask = detailledTask;
	}
	
	public UrlGenerator getUrlGenerator() {
		return urlGenerator;
	}

	public void setUrlGenerator(UrlGenerator urlGenerator) {
		this.urlGenerator = urlGenerator;
	}

	public String getUrlTask(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("taskId", new Long(taskId).toString());
		String url = getUrlGenerator().guestUrl(params);
		
		return url;
	}	
	public String getCasUrlTask(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("taskId", new Long(taskId).toString());
		String url = getUrlGenerator().casUrl(params);
		
		return url;
	}	

	public String goUrlTask(String taskId){
		this.taskId = new Long(taskId).longValue();
		detailledTask = getTaskFromDomainService(new Long(taskId).longValue());
		return "go_taskDetailPage";
	}
	public void genereException() throws Exception {
		throw new Exception("Bonjour je suis une erreur !");
	}
	public void genereTaskException() throws TaskException {
		//imaginons un code qui génère une TaskException
		throw new TaskException("Moi je suis une Task Exception !");
	}

	@Override
	public void reset() {
		super.reset();
		currentTask = new Task();
		taskToEditOrDelete = new Task();
		System.out.println("Reset task controller");
	}

	public SmtpService getSmtpService() {
		return smtpService;
	}

	public void setSmtpService(SmtpService smtpService) {
		this.smtpService = smtpService;
	}
	
		
}