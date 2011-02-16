/**
 * 
 */
package org.esupportail.example.web.beans;

import java.io.Serializable;

import org.esupportail.commons.web.controllers.Resettable;


/**
 * @author cleprous
 *
 */
public class UserBean implements Serializable, Resettable {

	/*
	 *************************** PROPERTIES ******************************** */
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 9076530476759057351L;

	/**
	 * Id of the user.
	 */
	private String id;
	
    /**
	 * Display Name of the user.
	 */
    private String displayName;
	
	
	

	/*
	 *************************** INIT ************************************** */

    /**
	 * Constructors.
	 */
	public UserBean() {
		super();
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserBean#" + hashCode() + "[id=[" + id + "], displayName=[" + displayName 
		+ "]]";
	}
    
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		UserBean other = (UserBean) obj;
		if (displayName == null) {
			if (other.displayName != null) { return false; }
		} else if (!displayName.equals(other.displayName)) { return false; }
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) { return false; }
		return true;
	}
	
	/*
	 *************************** METHODS *********************************** */

	
	@Override
	public void reset() {
		//permet de vider le formulaire
		id = null;
		displayName = null;
		
	}
	
	/*
	 *************************** ACCESSORS ********************************* */
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}


	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}


	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}


	



}
