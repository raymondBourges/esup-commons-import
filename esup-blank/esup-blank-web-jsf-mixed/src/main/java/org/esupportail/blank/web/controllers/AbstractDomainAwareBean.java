package org.esupportail.blank.web.controllers;

import org.esupportail.blank.domain.DomainService;
import org.esupportail.blank.domain.beans.User;
import org.esupportail.commons.services.application.ApplicationService;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.web.controllers.Resettable;
import org.springframework.beans.factory.InitializingBean;

/**
 * An abstract class inherited by all the beans for them to get:
 * - the domain service (domainService).
 */
public abstract class AbstractDomainAwareBean implements InitializingBean, Resettable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3917164987541810598L;

	/**
	 * A logger.
	 */
	@SuppressWarnings("unused")
	private final Logger logger = new LoggerImpl(this.getClass());
	
	/**
	 * see {@link DomainService}.
	 */
	private DomainService domainService;
	
	/**
	 * see {@link ApplicationService}.
	 */
	private ApplicationService applicationService;
	
	/**
	 * Constructor.
	 */
	protected AbstractDomainAwareBean() {
		super();
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(this.domainService, 
				"property domainService of class " + this.getClass().getName() + " can not be null");
		afterPropertiesSetInternal();
		reset();
	}

	/**
	 * This method is run once the object has been initialized, just before reset().
	 */
	protected void afterPropertiesSetInternal() {
		// override this method
	}
	
	@Override
	public void reset() {
		// nothing to reset		
	}

	/**
	 * @return the current user.
	 */
	protected User getCurrentUser() {
		// this method should be overriden
		return null;
	}

	/**
	 * @return the pplicationService
	 */
	public ApplicationService getApplicationService() {
		return applicationService;
	}

	/**
	 * @param applicationService
	 */
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * @param domainService
	 */
	public void setDomainService(final DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * @return the domainService
	 */
	public DomainService getDomainService() {
		return domainService;
	}

}
