/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank-primeFaces
 */
package org.esupportail.blankpf.domain;

import java.io.Serializable;

import org.esupportail.commons.exceptions.ConfigException;
import org.esupportail.commons.services.application.Version;
//import org.esupportail.blankpf.commons.web.beans.Paginator;

/**
 * The domain service interface.
 */
public interface DomainService extends Serializable {

	//////////////////////////////////////////////////////////////
	// VersionManager
	//////////////////////////////////////////////////////////////
	
	/**
	 * @return the database version.
	 * @throws ConfigException when the database is not initialized
	 */
	Version getDatabaseVersion() throws ConfigException;
	
	/**
	 * Set the database version.
	 * @param version 
	 */
	void setDatabaseVersion(Version version);
	
	/**
	 * Set the database version.
	 * @param version 
	 */
	void setDatabaseVersion(String version);
	
}
