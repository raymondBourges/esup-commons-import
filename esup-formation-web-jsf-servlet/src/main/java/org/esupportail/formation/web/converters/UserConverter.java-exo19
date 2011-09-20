package org.esupportail.formation.web.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.esupportail.commons.utils.Assert;
import org.esupportail.formation.domain.DomainService;
import org.esupportail.formation.domain.beans.User;
import org.springframework.beans.factory.InitializingBean;

public class UserConverter implements Converter,InitializingBean,Serializable {
	
	/**
	 *   
	 */
	private static final long serialVersionUID = 8067432240105625494L;
	
	DomainService domainService; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.domainService, "property domainService of class " 
				+ this.getClass().getName() + " can not be null");
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		User u=domainService.getUser(arg2);
		return u;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (arg2!=null && (arg2 instanceof User))
			return ((User)arg2).getLogin();
		else
			return null;
	}

	public DomainService getDomainService() {
		return domainService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

}
