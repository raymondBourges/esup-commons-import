/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.esupportail.commons.exceptions.ConfigException;
import org.esupportail.commons.utils.BeanUtils;
import org.esupportail.example.services.remote.Information;
import org.esupportail.example.web.deepLinking.DeepLinkingRedirectorImpl;


/**
 * A bean to manage files.
 */
public class AboutController extends AbstractContextAwareController {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 6667900188212342128L;

	/**
	 * The name of the bean that gives access to the web service. 
	 */
	private static final String REMOTE_INFORMATION_BEAN = "remoteInformation";

	/**
	 * The URL.
	 */
	private String url;
	
	/**
	 * The URL via CAS.
	 */
	private String urlViaCas;
	
	/**
	 * Constructor.
	 */
	public AboutController() {
		super();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		Map<String, String> params = new HashMap<String, String>();
		params.put(DeepLinkingRedirectorImpl.PAGE_PARAM, DeepLinkingRedirectorImpl.ABOUT_VALUE);
		url = getUrlGenerator().guestUrl(params);
		urlViaCas = getUrlGenerator().casUrl(params);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode();
	}

	/**
	 * @return true if the current user is allowed to view the page.
	 */
	public boolean isPageAuthorized() {
		return true;
	}
	
	/**
	 * JSF callback.
	 * @return A String.
	 */
	public String enter() {
		if (!isPageAuthorized()) {
			addUnauthorizedActionMessage();
			return null;
		}
		return "navigationAbout";
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the urlViaCas
	 */
	public String getUrlViaCas() {
		return urlViaCas;
	}
	
	/**
	 * @return the remote service.
	 */
	public Information getRemoteService() {
		Information remoteService = (Information) BeanUtils.getBean(REMOTE_INFORMATION_BEAN);
		if (remoteService == null) {
			throw new ConfigException("could not load bean [" + REMOTE_INFORMATION_BEAN + "]");
		}
		return remoteService;
	}	
	
	/**
	 * JSF callback.
	 * @return A String.
	 */
	public String getRemoteVersion() {
		return getRemoteService().getVersion();
	}	
	
	/**
	 * JSF callback.
	 * @return A String.
	 */
	public String getRemoteCopyright() {
		return getRemoteService().getCopyright();
	}	
	
	/**
	 * JSF callback.
	 * @return A String.
	 */
	public int getRemoteDepartmentCount() {
		return getRemoteService().getDepartmentCount();
	}	
	
	/**
	 * JSF callback.
	 * @return A String.
	 */
	public String throwException() {
		throw new ConfigException("test");
	}


}
