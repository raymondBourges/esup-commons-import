/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.esupportail.example.web.deepLinking.DeepLinkingRedirectorImpl;

/**
 * A bean to manage edition.
 */
public class EditionController extends AbstractContextAwareController {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 5482900047068599511L;

	/**
	 * The text.
	 */
	private String text;
	
	/**
	 * The URL via CAS.
	 */
	private String urlViaCas;

	/**
	 * Constructor.
	 */
	public EditionController() {
		super();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		Map<String, String> params = new HashMap<String, String>();
		params.put(DeepLinkingRedirectorImpl.PAGE_PARAM, DeepLinkingRedirectorImpl.EDITION_VALUE);
		urlViaCas = getUrlGenerator().casUrl(params);
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		text = null;
	}
	
	/**
	 * @return true if the current user is allowed to view the page.
	 */
	public boolean isPageAuthorized() {
		return getCurrentUser() != null;
	}

	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String enter() {
		if (!isPageAuthorized()) {
			addUnauthorizedActionMessage();
			return null;
		}
		return "navigationEdition";
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() 
		+ "[text=[" + text + "]]";
	}

	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String update() {
		return null;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * @return the URL via CAS.
	 */
	public String getUrlViaCas() {
		return urlViaCas;
	}

}
