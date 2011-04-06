/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.services.remote; 

import org.esupportail.commons.services.application.ApplicationService;
import org.esupportail.commons.services.remote.AbstractIpProtectedWebService;
import org.esupportail.commons.utils.Assert;
import org.esupportail.example.domain.DomainService;

/**
 * The basic implementation of the information remote service.
 */
public class InformationImpl extends AbstractIpProtectedWebService implements Information {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 4480257087458550019L;

	/**
	 * The application service.
	 */
	private ApplicationService applicationService;
	
	/**
	 * The domain service.
	 */
	private DomainService domainService;
	
	/**
	 * Bean constructor.
	 */
	public InformationImpl() {
		super();
	}

	/**
	 * @see org.esupportail.commons.services.remote.AbstractIpProtectedWebService#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		Assert.notNull(applicationService, 
				"property applicationService of class " + this.getClass().getName() 
				+ " can not be null");
		Assert.notNull(domainService, 
				"property domainService of class " + this.getClass().getName() 
				+ " can not be null");
	}
	
	/**
	 * @see org.esupportail.example.services.remote.Information#getVersion()
	 */
	public String getVersion() {
		return applicationService.getVersion().toString();
	}

	/**
	 * @see org.esupportail.example.services.remote.Information#getCopyright()
	 */
	public String getCopyright() {
		return applicationService.getCopyright();
	}

	/**
	 * @see org.esupportail.example.services.remote.Information#getDepartmentCount()
	 */
	public int getDepartmentCount() {
		return domainService.getDepartments().size();
	}
	
	/**
	 * @param applicationService the applicationService to set
	 */
	public void setApplicationService(final ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * @param domainService the domainService to set
	 */
	public void setDomainService(final DomainService domainService) {
		this.domainService = domainService;
	}

}
