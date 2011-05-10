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
public class TodoPrivateController extends AbstractContextAwareController {

	/*
	 ******************* PROPERTIES ******************** */

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 7764232092034490357L;


	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(this.getClass());

	
	
	/*
	 ******************* INIT ******************** */

	/**
	 * Bean constructor.
	 */
	public TodoPrivateController() {
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
	 ******************* METHODS ******************** */
	
	public String getCurrentUserId() {
		logger.debug("Dans getCurrentUserId");
        if (this.getCurrentUser()!=null){
            return this.getCurrentUser().getId();
        }
        else
            return null;
    }

	/*
	 ******************* CALLBACK ******************** */

	/**
	 * @return String
	 */
	public String goTodoPrivateDemo() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering goTodoPrivateDemo return go_todo_demo");
		}
		return NavigationRulesConst.todoPrivate;
	}
	
}
