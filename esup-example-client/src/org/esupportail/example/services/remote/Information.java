/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.services.remote; 


/**
 * The interface of the information remote service.
 */
public interface Information {

	/**
	 * @return the version.
	 */
	String getVersion();
	
	/**
	 * @return the copyright.
	 */
	String getCopyright();
	
	/**
	 * @return the number of departments.
	 */
	int getDepartmentCount();

}
