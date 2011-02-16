/**
 * ESUP-Portail example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.controllers;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.esupportail.example.services.i18n.BundleService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public class HelpController implements Serializable, InitializingBean {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = -7663673452311942420L;

	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(this.getClass());

	private String helpUrl;

	/**
	 * Constructor.
	 */
	public HelpController() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		if (this.helpUrl == null) {
			String[] args = {"helpUrl", this.getClass().getName()};
			throw new Exception(BundleService
					.getString("CONFIG_EXCEPTION.TITLE", args));
		}
	}

	/**
	 * @param helpUrl
	 *            the helpUrl to set
	 */
	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	/**
	 * @return an Url (with the good host, port and context...).
	 */
	public String getPrintUrl() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getExternalContext().getRequestContextPath()
				+ "/stylesheets/print/help.pdf.jsf";
	}

}
