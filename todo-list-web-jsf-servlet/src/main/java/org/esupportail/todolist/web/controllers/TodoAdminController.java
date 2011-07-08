/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.todolist.web.controllers;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.todolist.web.controllers.AbstractContextAwareController;
import org.esupportail.todolist.web.utils.NavigationRulesConst;


/**
 * A visual bean for the welcome page.
 */
public class TodoAdminController extends AbstractContextAwareController {

	/*
	 ******************* PROPERTIES ******************** */

	/**
	 * Serial
	 */

	private static final long serialVersionUID = -4238012751227011828L;


	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(this.getClass());

	
	
	/*
	 ******************* INIT ******************** */

	/**
	 * Bean constructor.
	 */
	public TodoAdminController() {
		super();
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
	public String goTodoAdminDemo() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering goTodoAdminDemo return go_todo_admin_demo");
		}
		return NavigationRulesConst.todoAdmin;
	}

	
	
}
