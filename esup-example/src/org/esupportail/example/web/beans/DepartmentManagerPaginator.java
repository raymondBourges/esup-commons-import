/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.beans; 

import java.util.List;

import org.esupportail.commons.web.beans.ListPaginator;
import org.esupportail.example.domain.DomainService;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.DepartmentManager;

/** 
 * A paginator for department manager.
 */ 
public class DepartmentManagerPaginator 
extends ListPaginator<DepartmentManager> {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -7526392733769292280L;

	/**
	 * The domain service.
	 */
	private DomainService domainService;
	
	/**
	 * The department.
	 */
	private Department department;
	
	/**
	 * Constructor.
	 * @param domainService 
	 */
	public DepartmentManagerPaginator(
			final DomainService domainService) {
		super(null, 0);
		this.domainService = domainService;
	}

	/**
	 * @see org.esupportail.commons.web.beans.ListPaginator#getData()
	 */
	@Override
	protected List<DepartmentManager> getData() {
		return domainService.getDepartmentManagers(department);
	}

	/**
	 * @param department
	 * @return the object itself
	 */
	public DepartmentManagerPaginator setDepartment(final Department department) {
		this.department = department;
		forceReload();
		return this;
	}

}

