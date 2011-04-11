/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.beans;

import java.io.File;
import java.io.Serializable;

/**
 * A class to print files.
 */
public class PrintableFile implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -9160545699643747914L;

	/**
	 * The file.
	 */
	private File file;
	
	/**
	 * Constructor.
	 * @param file 
	 */
	public PrintableFile(
			final File file) {
		super();
		this.file = file;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return file.getName();
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return file.length();
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return null;
	}

}
