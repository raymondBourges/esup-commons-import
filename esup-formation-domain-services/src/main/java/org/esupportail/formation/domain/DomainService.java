/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.domain;

import java.io.Serializable;
import java.util.List;

import org.esupportail.formation.domain.beans.User;
import org.esupportail.formation.domain.beans.Task;

/**
 * @author Yves Deschamps (Universite de Lille 1) - 2010
 * 
 */
public interface DomainService extends Serializable {
	
	/**
	 * @param uid
	 * @return a user.
	 */
	User getUser(String uid);
	List<Task> getTasks();
	void addTask(Task task);
}
