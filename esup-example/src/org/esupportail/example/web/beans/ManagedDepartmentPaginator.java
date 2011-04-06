/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.beans; 

import java.util.List;

import org.esupportail.commons.web.beans.ListPaginator;
import org.esupportail.example.domain.DomainService;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.User;

/** 
 * A paginator for managed departments.
 */ 
public class ManagedDepartmentPaginator extends ListPaginator<Department> {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 6592815804622855402L;

	/**
	 * The domain service.
	 */
	private DomainService domainService;
	
	/**
	 * The user.
	 */
	private User user;
	
	/**
	 * Constructor.
	 * @param domainService 
	 */
	public ManagedDepartmentPaginator(
			final DomainService domainService) {
		super(null, 0);
		this.domainService = domainService;
	}

	/**
	 * @see org.esupportail.commons.web.beans.ListPaginator#getData()
	 */
	@Override
	protected List<Department> getData() {
		return domainService.getManagedDepartments(user);
	} 
	
	/**
	 * @param user the user to set
	 */
	public void setUser(final User user) {
		this.user = user;
		forceReload();
	}
	
}

