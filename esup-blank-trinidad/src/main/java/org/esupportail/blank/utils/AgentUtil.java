/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.blank.utils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.esupportail.blank.services.i18n.BundleService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public class AgentUtil implements Serializable, InitializingBean {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = -1259495940256943174L;

	private final Logger logger = Logger.getLogger(this.getClass());

	private String phoneFamily;

	private Map<String, String> skins;
	
	private boolean mobile;

	/**
	 * Constructor.
	 */
	public AgentUtil() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		if (this.skins == null) {
			String[] args = {"skins", this.getClass().getName()};
			throw new Exception(BundleService
					.getString("CONFIG_EXCEPTION.TITLE", args));
		}
	}

	/**
	 * @return the skin from user-agent detect.
	 */
	public String getPhoneFamily() {
		if (phoneFamily == null) {
			String agent = null;
			FacesContext fc = FacesContext.getCurrentInstance();
			agent = fc.getExternalContext().getRequestHeaderMap().get(
					"User-Agent");
			logger.info("User-Agent: " + agent);
			for (Iterator<String> i = skins.keySet().iterator(); i.hasNext();) {
				String key = i.next();
				if (agent != null && agent.indexOf(key) > -1) {
					phoneFamily = skins.get(key);
					mobile = true;
					return phoneFamily;
				}
			}
			phoneFamily = "minimalFamily";
		}
		return phoneFamily;
	}

	/**
	 * @return the skins
	 */
	public Map<String, String> getSkins() {
		return skins;
	}

	/**
	 * @param skins
	 *            the skins to set
	 */
	public void setSkins(Map<String, String> skins) {
		this.skins = skins;
	}

	/**
	 * @return the mobile
	 */
	public boolean isMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

}
