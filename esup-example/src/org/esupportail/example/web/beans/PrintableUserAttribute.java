/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.beans;

import java.io.Serializable;
import java.util.List;

/** 
 * A basic user attribute, with a name and values.
 */
public final class PrintableUserAttribute implements Serializable {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 2430836765769889371L;

	/**
	 * The name of the group.
	 */
	private String name;
	
	/**
	 * The values of the attribute.
	 */
	private List<String> values;
	
	/**
	 * Constructor.
	 * @param name
	 * @param values
	 */
	public PrintableUserAttribute(
			final String name,
			final List<String> values) {
		super();
		this.name = name;
		this.values = values;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() + "[name=[" + name + "], values=" + values + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

}
