/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.deepLinking;

import java.util.Map;

import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.web.deepLinking.AbstractDeepLinkingRedirector;
import org.esupportail.example.domain.DomainService;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.exceptions.DepartmentNotFoundException;
import org.esupportail.example.web.controllers.AboutController;
import org.esupportail.example.web.controllers.DepartmentsController;
import org.esupportail.example.web.controllers.EditionController;
import org.esupportail.example.web.controllers.SessionController;
import org.esupportail.example.web.controllers.WelcomeController;
import org.springframework.util.StringUtils;

/**
 * The esup-example implementation of the page redirector (for deep linking).
 */
public class DeepLinkingRedirectorImpl extends AbstractDeepLinkingRedirector {
	
	/**
	 * A parameter name.
	 */
	public static final String PAGE_PARAM = "page";
	
	/**
	 * A parameter name.
	 */
	public static final String DEPARTMENT_ID_PARAM = "departmentId";
	
	/**
	 * A parameter value.
	 */
	public static final String ABOUT_VALUE = "about";
	
	/**
	 * A parameter value.
	 */
	public static final String DEPARTMENT_VIEW_VALUE = "departmentView";
	
	/**
	 * A parameter value.
	 */
	public static final String EDITION_VALUE = "edition";
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 7724449836325410057L;

	/**
	 * The domain service.
	 */
	private DomainService domainService;
	
	/**
	 * The session controller.
	 */
	private SessionController sessionController;
	
	/**
	 * The welcome controller.
	 */
	private WelcomeController welcomeController;
	
	/**
	 * The departments controller.
	 */
	private DepartmentsController departmentsController;
	
	/**
	 * The about controller.
	 */
	private AboutController aboutController;
	
	/**
	 * The edition controller.
	 */
	private EditionController editionController;
	
	/**
	 * Bean constructor.
	 */
	public DeepLinkingRedirectorImpl() {
		super();
	}

	/**
	 * @see org.esupportail.commons.beans.AbstractI18nAwareBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		Assert.notNull(this.domainService, "property domainService of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.sessionController, "property sessionController of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.departmentsController, "property departmentsController of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.aboutController, "property aboutController of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.editionController, "property editionController of class " 
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(this.welcomeController, "property welcomeController of class " 
				+ this.getClass().getName() + " can not be null");
	}

	/**
	 * @see org.esupportail.commons.web.deepLinking.DeepLinkingRedirector#redirect(java.util.Map)
	 */
	public String redirect(
			final Map<String, String> params) {
		sessionController.resetSessionLocale();
		// do this to be sure that the welcome page will be initialized
		welcomeController.enter();
		if (params == null) {
			return null;
		}
		if (ABOUT_VALUE.equals(params.get(PAGE_PARAM))) {
			aboutController.enter();
			return "/stylesheets/about.jsp";
		}
		if (EDITION_VALUE.equals(params.get(PAGE_PARAM))) {
			editionController.enter();
			return "/stylesheets/edition.jsp";
		}
		if (DEPARTMENT_VIEW_VALUE.equals(params.get(PAGE_PARAM))) {
			if (params.get(DEPARTMENT_ID_PARAM) == null) {
				addErrorMessageMissingParameter(DEPARTMENT_ID_PARAM);
				return null;
			}
			Long departmentId;
			try {
				departmentId = Long.valueOf(params.get(DEPARTMENT_ID_PARAM));
			} catch (NumberFormatException e) {
				addErrorMessageInvalidParameter(DEPARTMENT_ID_PARAM, params.get(DEPARTMENT_ID_PARAM));
				return null;
			}
			Department department;
			try {
				department = domainService.getDepartment(departmentId);
			} catch (DepartmentNotFoundException e) {
				addErrorMessage(null, "DEEP_LINKS.MESSAGE.DEPARTMENT_NOT_FOUND", 
						departmentId.toString());
				if (departmentsController.isPageAuthorized()) {
					return "/stylesheets/departments.jsp";
				}
				return null;
			}
			if (!domainService.userCanViewDepartment(sessionController.getCurrentUser(), department)) {
				addErrorMessage(null, "DEEP_LINKS.MESSAGE.DEPARTMENT_VIEW_NOT_ALLOWED", 
						department.getLabel());
				if (departmentsController.isPageAuthorized()) {
					return "/stylesheets/departments.jsp";
				}
				return null;
			}
			departmentsController.enter();
			departmentsController.setDepartment(department);
			return "/stylesheets/departmentView.jsp";
		}
		if (StringUtils.hasText(params.get(PAGE_PARAM))) {
			addErrorMessageInvalidParameter(PAGE_PARAM, params.get(PAGE_PARAM));
		}
		return null;
	}

	/**
	 * @param departmentsController the departmentsController to set
	 */
	public void setDepartmentsController(final DepartmentsController departmentsController) {
		this.departmentsController = departmentsController;
	}

	/**
	 * @param sessionController the sessionController to set
	 */
	public void setSessionController(final SessionController sessionController) {
		this.sessionController = sessionController;
	}

	/**
	 * @param domainService the domainService to set
	 */
	public void setDomainService(final DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * @param aboutController the aboutController to set
	 */
	public void setAboutController(final AboutController aboutController) {
		this.aboutController = aboutController;
	}

	/**
	 * @param editionController the editionController to set
	 */
	public void setEditionController(final EditionController editionController) {
		this.editionController = editionController;
	}

	/**
	 * @param welcomeController the welcomeController to set
	 */
	public void setWelcomeController(final WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}

}
