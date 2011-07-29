package org.esupportail.formation.web.controllers;

import java.util.Date;
import java.util.List;

import org.esupportail.formation.domain.beans.Task;

public class TaskController extends AbstractContextAwareController {

	/**
	 * VersionId
	 */
	private static final long serialVersionUID = -872218604638760392L;
	
	public String getTestDao(){
		Task task=new Task();
		task.setDate(new Date());
		task.setTitle("titre de test");
		task.setPublicTask(true);
		getDomainService().addTask(task);
		
		List<Task> liste=getDomainService().getTasks();
		for (Task t : liste) {
			System.out.println(t.getId()+" : "+t.getTitle());
		}
		
		return "OK";
	}
}
