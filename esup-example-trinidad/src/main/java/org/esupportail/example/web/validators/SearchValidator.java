/**
 * ESUP-Portail example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.validators;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.esupportail.example.services.i18n.BundleService;

/**
 * @author Yves Deschamps (UniversitÈ de Lille 1) - 2010
 * 
 */
public class SearchValidator implements Validator, Serializable {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = -2750912643231267214L;

	/**
	 * Constructor.
	 */
	public SearchValidator() {
		super();
	}

	/**
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validate(FacesContext context, UIComponent component,
			Object toValidate) throws ValidatorException {
		int minimum = 2;
		int maximum = 10;
		String regex = "[A-z][ \\-‡‚‰ÈËÎÍÓÔÙˆ˘˚¸ÁA-z][ \\-‡‚‰ÈËÎÍÓÔÙˆ˘˚¸ÁA-z]*";
		String input = (String) toValidate;
		if (input == null || input.length() < minimum
				|| input.length() > maximum) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "", BundleService
							.getString("ADMINISTRATION.ERROR.SEARCH1")));
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		boolean matchFound = m.matches();
		if (!matchFound) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "", BundleService
							.getString("ADMINISTRATION.ERROR.SEARCH2")));
		}
	}

}
