/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.web.controllers;

import java.util.List;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.todolist.domain.beans.Task;
import org.esupportail.todolist.domain.beans.User;
import org.esupportail.todolist.web.beans.PublicTasksPaginator;
import org.esupportail.todolist.web.controllers.AbstractContextAwareController;
import org.esupportail.todolist.web.utils.NavigationRulesConst;


/**
 * A visual bean for the welcome page.
 */
public class TodoPublicController extends AbstractContextAwareController {

	/*
	 ******************* PROPERTIES ******************** */

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -4445423987226841665L;

	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(this.getClass());

	/*
	 ******************* INIT ******************** */

	/**
	 * Bean constructor.
	 */
	public TodoPublicController() {
		super();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode();
	}

	/**
	 * @see org.esupportail.todolist.web.controllers.AbstractDomainAwareBean#reset()
	 */
	@Override
	public void reset() {
		super.reset();		
	}

	/**
	 * @see org.esupportail.todolist.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
	}


	/*
	 ******************* CALLBACK ******************** */

	/**
	 * @return String
	 */
	public String goTodoDemo() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering goTodoDemo return go_todo_demo");
		}
		return  NavigationRulesConst.todo;
	}
	
	/*
	 ******************* Getters and setters ******************** */

}
