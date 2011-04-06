/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.beans; 

import java.util.List;

import org.esupportail.commons.web.beans.ListPaginator;
import org.esupportail.example.domain.DomainService;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.Thing;

/** 
 * A paginator for things.
 */ 
public class ThingPaginator extends ListPaginator<Thing> {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 5351945226908445094L;

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
	public ThingPaginator(
			final DomainService domainService) {
		super(null, 0);
		this.domainService = domainService;
	}

	/**
	 * @see org.esupportail.commons.web.beans.ListPaginator#getData()
	 */
	@Override
	protected List<Thing> getData() {
		return domainService.getThings(department);
	} 
	
	/**
	 * @param department
	 * @return the object itself.
	 */
	public ThingPaginator setDepartment(final Department department) {
		this.department = department;
		forceReload();
		return this;
	}
	
}

