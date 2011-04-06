/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.services.remote; 

import java.io.Serializable;


/**
 * The interface of the information remote service.
 */
public interface Information extends Serializable {

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
