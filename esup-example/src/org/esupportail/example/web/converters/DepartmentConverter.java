/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.web.controllers.AbstractDomainAwareBean;
import org.springframework.util.StringUtils;

/**
 * A JSF converter to pass Department instances.
 */
public class DepartmentConverter extends AbstractDomainAwareBean implements Converter {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -3325048201750353277L;

	/**
	 * Bean constructor.
	 */
	public DepartmentConverter() {
		super();
	}

	/**
	 * @see javax.faces.convert.Converter#getAsObject(
	 * javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(
			@SuppressWarnings("unused") final FacesContext context, 
			@SuppressWarnings("unused") final UIComponent component, 
			final String value) {
		if (!StringUtils.hasText(value)) {
			return null;
		}
		try {
			long longValue = Long.valueOf(value);
			return getDomainService().getDepartment(longValue);
		} catch (NumberFormatException e) {
			throw new UnsupportedOperationException(
					"could not convert String [" + value + "] to a Department.", e);
		}
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(
	 * javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(
			@SuppressWarnings("unused") final FacesContext context, 
			@SuppressWarnings("unused") final UIComponent component, 
			final Object value) {
		if (value == null || !StringUtils.hasText(value.toString())) {
			return "";
		}
		if (!(value instanceof Department)) {
			throw new UnsupportedOperationException(
					"object " + value + " is not a Department.");
		}
		Department department = (Department) value;
		return Long.toString(department.getId());
	}

}
