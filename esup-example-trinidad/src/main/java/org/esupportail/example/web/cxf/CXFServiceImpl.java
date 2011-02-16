/**
 * ESUP-Portail Directory Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.cxf;

import java.util.List;

import org.esupportail.example.domain.DomainService;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.services.i18n.BundleService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
@SuppressWarnings("restriction")
public class CXFServiceImpl implements CXFService, InitializingBean {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = 5562208937328953456L;

	private DomainService domainService;

	/**
	 * Constructor.
	 */
	public CXFServiceImpl() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		if (this.domainService == null) {
			String[] args = {"domainService", this.getClass().getName()};
			throw new Exception(BundleService
					.getString("CONFIG_EXCEPTION.TITLE", args));
		}
	}

	/**
	 * @param domainService
	 *            the domainService to set
	 */
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * @see org.esupportail.example.web.cxf.CXFService#getAdministrators()
	 */
	public List<User> getAdministrators() {
		return domainService.getAdministrators();
	}

}