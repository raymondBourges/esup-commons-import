/**
 * CRI - Université de Rennes 1 - <nom du projet> - <année>
 * <url de gestion du projet>
 * Version de la norme de développement : <version de ce document> 
 */
package org.esupportail.todolist.web.beans;

import java.util.List;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.services.paginator.ListPaginator;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.web.controllers.Resettable;
import org.esupportail.todolist.domain.DomainService;
import org.esupportail.todolist.domain.beans.Task;

/**
 * @author pthomas
 * 
 */
public class TasksPaginator 
	extends ListPaginator<Task> 
	implements  Resettable {


	/**
	 * Serial Version number
	 */
	private static final long serialVersionUID = -469709091128889532L;
	/*
	 * ************************** PROPERTIES ********************************
	 */

	/**
	 * A logger.
	 */
	private static final Logger log = new LoggerImpl(TasksPaginator.class);

	private DomainService domainService = null ; 

	/*
	 * ************************** INIT **************************************
	 */
	public void afterPropertiesSetInternal() {
		Assert.notNull(this.domainService, "property domainService of class " 
				+ this.getClass().getName() + " can not be null");
	}
	/**
	 * Constructor.
	 */
	public TasksPaginator() {
		super();
	}

	/**
	 * @see org.esupportail.commons.web.beans.AbstractPaginator#reset()
	 */
	
	public void reset() {
		if (log.isDebugEnabled()) {
			log.debug("entering TasksPaginator.reset()");
		}
		super.reset();
	}
	protected List<Task> getData() {
		return getDomainService().getTasks();
	}

	/*
	 * ************************** ACCESSORS *********************************
	 */


	public DomainService getDomainService() {
		return domainService;
	}
	
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	
	}
	
}