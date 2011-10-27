/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.portlet.domain;

import java.io.Serializable;
import java.util.List;

import org.esupportail.formation.domain.Task;
import org.esupportail.formation.portlet.domain.beans.User;

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
	public List<Task> getTasks(String login,String wsdl);
	public List<Task> getAllTasks(String wsdl);
}
