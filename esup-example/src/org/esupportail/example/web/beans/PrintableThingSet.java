/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.beans;

import java.io.Serializable;
import java.util.List;

import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.Thing;


/**
 * A class to temporally store the things of a user for a department.
 */
public class PrintableThingSet implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 1086791286482898876L;

	/**
	 * The department.
	 */
	private Department department;

	/**
	 * The things.
	 */
	private List<Thing> things;

	/**
	 * Constructor.
	 * @param department 
	 * @param things  
	 */
	public PrintableThingSet(
			final Department department,
			final List<Thing> things) {
		super();
		this.department = department;
		this.things = things;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the things
	 */
	public List<Thing> getThings() {
		return things;
	}
	
}
